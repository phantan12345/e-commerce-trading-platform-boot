/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.service.Messsages.DTO.*;
import com.ou.demo.service.Messsages.IMessagesService;
import com.ou.demo.service.Users.DTO.CurrentUser;
import com.ou.demo.service.Users.DTO.UsersDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/message")
public class MessagesController {

    @Autowired
    private IMessagesService messagesService;

    @GetMapping("/{pageNumber}/{pageSize}")
    public ResponseEntity<List<?>> getUserMessages(@CurrentUser UsersDto user, @PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize) {
        return ResponseEntity.ok().body(messagesService.getUserMessages(user, pageNumber, pageSize));
    }

    @GetMapping("{chatRecipientId}")
    public ResponseEntity<List<MessagesDto>> getUserMessagesWithUser(@CurrentUser UsersDto user, @PathVariable("chatRecipientId") int chatRecipientId) {
        return ResponseEntity.ok().body(messagesService.getUserMessagesWithUser(user, chatRecipientId));
    }

    @PostMapping("")
    public ResponseEntity<MessagesDto> postMessage(@CurrentUser UsersDto loggedInUser, @RequestBody MessagesDto messagesDto) {
        MessagesDto savedMessagesDto = messagesService.postMessage(loggedInUser, messagesDto);
        return ResponseEntity.ok().body(savedMessagesDto);
    }

    @PutMapping("/read")
    public void markMessagesReadForUsers(@CurrentUser UsersDto user, @RequestBody UpdateMessageStatusDto updateMessageStatusDto) {
        messagesService.markMessagesReadForUsers(user.getId(), updateMessageStatusDto);
    }
}
