/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Messsages;

import com.ou.demo.pojos.Messages;
import com.ou.demo.pojos.User;
import com.ou.demo.service.Messsages.DTO.MessageSummaryDto;
import com.ou.demo.service.Messsages.DTO.MessagesDto;
import com.ou.demo.service.Messsages.DTO.UpdateMessageStatusDto;
import com.ou.demo.service.Users.DTO.UsersDto;
import com.ou.demo.util.Service.ICrud;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IMessagesService extends ICrud<Messages, MessagesDto> {

    List<MessageSummaryDto> getUserMessages(UsersDto userId, Integer pageNumber, Integer pageSize);

    List<MessagesDto> getUserMessagesWithUser(UsersDto loggedInUserId, Integer chatRecipientId );

    MessagesDto postMessage(UsersDto loggedInUser, MessagesDto messagesDto);

    void markMessagesReadForUsers(Integer loggedInUserId, UpdateMessageStatusDto updateMessageStatusDto);
}
