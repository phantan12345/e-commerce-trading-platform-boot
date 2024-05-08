/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Messsages;

import com.ou.demo.pojos.Messages;
import com.ou.demo.pojos.Store;
import com.ou.demo.pojos.User;
import com.ou.demo.repositories.MessagesRepository;
import com.ou.demo.repositories.UserRepository;
import com.ou.demo.service.Messsages.DTO.MessageSummaryDto;
import com.ou.demo.service.Messsages.DTO.MessagesDto;
import com.ou.demo.service.Messsages.DTO.UpdateMessageStatusDto;
import com.ou.demo.service.Stores.StoreService;
import com.ou.demo.service.Users.DTO.UsersDto;
import com.ou.demo.service.Users.IUserService;
import com.ou.demo.util.Service.Crud;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADMIN
 */
@Service
public class MesssagesService extends Crud<Messages, MessagesDto> implements IMessagesService {

    @Autowired
    private MessagesRepository messagesRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private IUserService userService;

    @Autowired
    private StoreService storeService;

    //    SELECT * FROM messages WHERE (sent_by = 4 AND sent_to = 6) OR (sent_by = 6 AND sent_to=4) ORDER BY message_id
    public List<MessageSummaryDto> getUserMessages(UsersDto userId, Integer pageNumber, Integer pageSize) {
        Pageable paginationObject = PageRequest.of(pageNumber, pageSize);

        List<Object[]> messages = new ArrayList<>();

        messages = messagesRepository.getUserDistinctMessages(userId.getId());

        return messages.stream()
                .map(messageSummaryObject -> new MessageSummaryDto(
                userId.getId(),
                Integer.valueOf(messageSummaryObject[0].toString()),
                Integer.valueOf(messageSummaryObject[1].toString()),
                messageSummaryObject[2].toString(),
                messageSummaryObject[3].toString(),
                messageSummaryObject[4].toString(),
                Integer.valueOf(messageSummaryObject[5].toString()),
                Integer.valueOf(messageSummaryObject[6].toString())))
                .toList();
    }

    @Override
    public List<MessagesDto> getUserMessagesWithUser(UsersDto loggedInUserId, Integer chatRecipientId, Integer pageNumber, Integer pageSize) {
        Pageable paginationObject = PageRequest.of(pageNumber, pageSize);

        List<Messages> messages = messagesRepository.getUserMessagesWithUser(loggedInUserId.getId(), chatRecipientId);

        return messages.stream().map(message -> MessagesDto.builder()
                .sentBy(message.getSentBy())
                .message(message.getMessage())
                .messageId(message.getId())
                .status(message.getStatus())
                .sentTo(message.getSentTo())
                .build()).toList();
    }

    @Override
    public MessagesDto postMessage(UsersDto loggedInUser, MessagesDto messagesDto) {

        Messages savedMessage = messagesRepository.save(
                Messages.builder()
                        .sentBy(new User(loggedInUser.getId()))
                        .sentTo(new User(messagesDto.getSentTo().getId()))
                        .message(messagesDto.getMessage())
                        .status("U")
                        .build());

        MessagesDto savedMessagesDto = MessagesDto.builder()
                .message(savedMessage.getMessage())
                .sentTo(savedMessage.getSentTo())
                .sentBy(savedMessage.getSentBy())
                .messageId(savedMessage.getId())
                .status(savedMessage.getStatus())
                .build();

        MessageSummaryDto dto = new MessageSummaryDto(savedMessage.getSentTo().getId(),
                loggedInUser.getId(),
                savedMessagesDto.getMessageId(),
                loggedInUser.getName(),
                loggedInUser.getAvatar(),
                savedMessage.getMessage(),
                1,
                loggedInUser.getId()
        );
        messagingTemplate.convertAndSendToUser(String.valueOf(savedMessagesDto.getSentTo().getId()), "/reply", dto);
        return savedMessagesDto;
    }

    @Override
    @Transactional
    public void markMessagesReadForUsers(Integer loggedInUserId, UpdateMessageStatusDto updateMessageStatusDto) {
        Integer chatRecipientId = updateMessageStatusDto.getChatRecipientId();
        messagesRepository.markMessagesReadForUsers(loggedInUserId, chatRecipientId);
    }

}
