/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.pojos.Store;
import com.ou.demo.pojos.User;
import com.ou.demo.service.Mails.MailService;
import com.ou.demo.service.Stores.DTO.StoreDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ou.demo.service.Orders.IOrderService;
import com.ou.demo.service.ProductStores.DTO.ProductStoreDto;
import com.ou.demo.service.ProductStores.ProductStoreService;
import com.ou.demo.service.Users.DTO.CurrentUser;
import com.ou.demo.service.Users.DTO.UsersDto;
import com.ou.demo.service.Users.IUserService;
import com.ou.demo.service.Stores.IStoreService;

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

    private IStoreService storeService;

    private IUserService UserService;

    private IOrderService OrderService;

    private ProductStoreService ProductStoreService;

    @PostMapping("/store")
    public ResponseEntity<?> createStore(@CurrentUser UsersDto currentUser, @RequestBody StoreDTO s) {
        User user = UserService.findById(currentUser.getId());

        Store store = storeService.Create(s, user);
        return new ResponseEntity<>(
                store, HttpStatus.OK);

    }

    @GetMapping("/store")
    public ResponseEntity<?> getStore(@CurrentUser UsersDto currentUser) {
        Store s = storeService.findStoreById(currentUser.getId());

        ProductStoreDto dto = ProductStoreService.findAllByStore(s);
        if (dto == null) {
            return new ResponseEntity<>("orror find products",
                    HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(
                    dto, HttpStatus.OK);
        }
    }

    @GetMapping("/store/{id}")
    public ResponseEntity<?> getStore(@PathVariable("id") int id) {
        Store s = storeService.findStoreById(id);

        ProductStoreDto dto = ProductStoreService.findAllByStore(s);
        if (dto == null) {
            return new ResponseEntity<>("orror find products",
                    HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(
                    dto, HttpStatus.OK);
        }
    }

    @GetMapping("/stores")
    public ResponseEntity<?> getStores() {

        List<Store> store = storeService.getStores();

        if (store == null) {
            return new ResponseEntity<>("USER NOT ACCEPTED", HttpStatus.NOT_ACCEPTABLE);
        } else {
            return ResponseEntity.ok(store);

        }

    }

//    @GetMapping("/requestment")
//    public ResponseEntity<?> getRequestment() {
//
//        return new ResponseEntity<>(storeService.getRequestment(), HttpStatus.BAD_REQUEST);
//
//    }
//    @GetMapping("/stat/{id}")
//    public ResponseEntity<?> getStat(@PathVariable("id") int id, @RequestBody DateDto dto) {
//
//        Store s = storeService.findStoreById(id);
//        if (s != null) {
//            return ResponseEntity.ok().body(OrderService.stat(s, dto));
//        }
//        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
//
//    }
    @DeleteMapping("/store/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {

        return ResponseEntity.ok(storeService.Delete(id));

    }

}
