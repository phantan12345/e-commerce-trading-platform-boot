/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.dto.ProdcutDto;
import com.ou.demo.pojos.Store;
import com.ou.demo.pojos.User;
import com.ou.demo.service.ProductStoreService;
import com.ou.demo.service.StoreService;
import com.ou.demo.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductStoreController {

    @Autowired
    private ProductStoreService ProductStoreService;

    @Autowired
    private UserService UserService;
    
    @Autowired
    private StoreService StoreService;

    @GetMapping("product-store/")
    public ResponseEntity<?> getProduct() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = UserService.findByUsername(userDetails.getUsername());
            Store s=StoreService.findStoreByUserID(user);
            List<ProdcutDto> dto = ProductStoreService.findAllByStore(s);
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
