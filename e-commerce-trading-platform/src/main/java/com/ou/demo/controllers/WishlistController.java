/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.pojos.Category;
import com.ou.demo.pojos.Store;
import com.ou.demo.pojos.User;
import com.ou.demo.pojos.Wishlist;
import com.ou.demo.service.Categorys.DTO.CategoryDTO;
import com.ou.demo.service.Users.DTO.CurrentUser;
import com.ou.demo.service.Users.DTO.UsersDto;
import com.ou.demo.service.Wishlists.DTO.WishlistDTO;
import com.ou.demo.service.Wishlists.IWishlistService;
import com.ou.demo.service.Wishlists.WishlistService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ou.demo.service.Users.IUserService;

/**
 *
 * @author ADMIN
 */
@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class WishlistController {

    private IWishlistService wishlistService;

    private IUserService UserService;

    @GetMapping("/wishlist")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(wishlistService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/wishlist")
    public ResponseEntity<?> create(@CurrentUser UsersDto currentUser, @RequestBody @Valid WishlistDTO input) {

        User user = UserService.findById(currentUser.getId());

        input.setUserId(user);
        Wishlist wishlist = wishlistService.Create(input);
        if (wishlist == null) {
            return new ResponseEntity<>("error create", HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(wishlist, HttpStatus.OK);

    }
}
