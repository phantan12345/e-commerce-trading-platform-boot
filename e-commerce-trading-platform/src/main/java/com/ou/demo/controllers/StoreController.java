/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.dto.DateDto;
import com.ou.demo.dto.Mail;
import com.ou.demo.dto.ProdcutDto;
import com.ou.demo.pojos.Store;
import com.ou.demo.pojos.User;
import com.ou.demo.service.MailService;
import com.ou.demo.service.OrderService;
import com.ou.demo.service.StoreService;
import com.ou.demo.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@CrossOrigin

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class StoreController {

    private MailService MailService;

    private StoreService storeService;

    private UserService UserService;

    private OrderService OrderService;

    @PostMapping("store/")
    public ResponseEntity<?> createStore(@RequestBody Store s) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = UserService.findByUsername(userDetails.getUsername());
            
            Store store = storeService.Create(s, user);
            return new ResponseEntity<>(store == null ? "orror find products"
                    : new ResponseEntity(store, HttpStatus.OK), HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @GetMapping("store/")
    public ResponseEntity<?> getStore() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = UserService.findByUsername(userDetails.getUsername());

            Store store = storeService.findStoreByUserID(user);

            if (store.getActive() == Boolean.FALSE) {
                return new ResponseEntity<>("USER NOT ACCEPTED", HttpStatus.NOT_ACCEPTABLE);
            } else {
                return ResponseEntity.ok(store);

            }

        } else {
            return ResponseEntity.accepted().build();
        }
    }

    @PostMapping("/requestment/{id}")
    public ResponseEntity<?> requestment(@PathVariable(value = "id") int id) {

        Store s = storeService.findStoreById(id);
        if (s != null) {
            s.setActive(Boolean.TRUE);
//            s.getUserId().setRoleId(roleId);
            Mail mail = new Mail();
            mail.setMailFrom("2051050435tan@ou.edu.vn");
            mail.setMailTo(s.getUserId().getEmail());
            mail.setMailSubject("Spring Boot - Email Register");
            mail.setMailContent("BẠN ĐÃ ĐĂNG KÍ THÀNH CÔNG");
            MailService.sendEmail(mail);
            Store store = storeService.update(s);
            return new ResponseEntity<>(store == null ? "orror find products"
                    : new ResponseEntity(store, HttpStatus.NOT_MODIFIED), HttpStatus.OK);

        }
        return new ResponseEntity<>("no find store", HttpStatus.BAD_REQUEST);

    }
    
    
     @GetMapping("/requestment/")
    public ResponseEntity<?> getRequestment() {

       
        return new ResponseEntity<>(storeService.getRequestment(), HttpStatus.BAD_REQUEST);

    }

    @GetMapping("stat/{id}")
    public ResponseEntity<?> getStat(@PathVariable("id") int id, @RequestBody DateDto dto) {

        Store s = storeService.findStoreById(id);
        if (s != null) {
            return ResponseEntity.ok().body(OrderService.stat(s, dto));
        }
        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("store/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {

            Store store = storeService.findStoreById(id);

            if (store == null) {
                return new ResponseEntity<>("USER NOT ACCEPTED", HttpStatus.NOT_ACCEPTABLE);
            } else {
                return ResponseEntity.ok(storeService.delete(store));

            }

        } else {
            return ResponseEntity.accepted().build();
        }
    }
    

}
