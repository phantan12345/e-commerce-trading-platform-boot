/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.dto.Mail;
import com.ou.demo.dto.ProdcutDto;
import com.ou.demo.pojos.Store;
import com.ou.demo.pojos.User;
import com.ou.demo.service.MailService;
import com.ou.demo.service.StoreService;
import com.ou.demo.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@RestController
@RequestMapping("/api")
public class StoreController {

    @Autowired
    private MailService MailService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private UserService UserService;

    @PostMapping("store/")
    public ResponseEntity<?> createStore(@RequestBody Store s) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = UserService.findByUsername(userDetails.getUsername());

            Store store = storeService.Create(s, user);
            return ResponseEntity.ok().body(store);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("store/")
    public ResponseEntity<?> getStore() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = UserService.findByUsername(userDetails.getUsername());

            Store store = storeService.findStoreByUserID(user);
            return ResponseEntity.ok().body(store);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/requestment/{id}")
    public ResponseEntity<?> requestment(@PathVariable(value = "id") int id) {

        User user = UserService.findById(id);
        Store store = storeService.findStoreByUserID(user);
        if (store != null) {
            store.setActive(Boolean.TRUE);
            Mail mail = new Mail();
            mail.setMailFrom("2051050435tan@ou.edu.vn");
            mail.setMailTo(user.getEmail());
            mail.setMailSubject("Spring Boot - Email Register");
            mail.setMailContent("BẠN ĐÃ ĐĂNG KÍ THÀNH CÔNG");
            MailService.sendEmail(mail);
            return new ResponseEntity<>(storeService.update(store), HttpStatus.OK);

        }
        return new ResponseEntity<>("no find store", HttpStatus.CREATED);

    }

}
