/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.service.Reviews.DTO.ReviewDto;
import com.ou.demo.pojos.Review;
import com.ou.demo.pojos.User;
import com.ou.demo.repositories.ProductReponsitory;
import com.ou.demo.service.Reviews.ReviewService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ou.demo.service.Products.IProductService;
import com.ou.demo.service.Users.DTO.CurrentUser;
import com.ou.demo.service.Users.DTO.UsersDto;
import com.ou.demo.service.Users.IUserService;

/**
 *
 * @author ADMIN
 */
@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ReviewController {

    private ReviewService ReviewService;

    private IUserService UserService;

    private ProductReponsitory ProductService;

    @PostMapping("/product/{id}/comment")
    public ResponseEntity<?> createCommentToProduct(@CurrentUser UsersDto currentUser, @RequestBody Review c, @PathVariable("id") int id) {
        User user = UserService.findById(currentUser.getId());
        Review Reriew = ReviewService.addComment(c, user, id, 0);

        return new ResponseEntity<>(Reriew, HttpStatus.OK);
    }

    //reply comment
    @PostMapping("/product/{Proid}/comment/{id}/comments")
    public ResponseEntity<?> replyToComent(@CurrentUser UsersDto currentUser, @RequestBody Review c, @PathVariable("Proid") int proId, @PathVariable("id") int id) {

        User user = UserService.findById(currentUser.getId());
        Review Reriew = ReviewService.addComment(c, user, proId, id);
        return new ResponseEntity<>(Reriew, HttpStatus.OK);

    }

    @GetMapping("/product/{id}/comment")
    public ResponseEntity<List<?>> getAllCommentsByProduct(@PathVariable("id") int id) {

        return new ResponseEntity<>(ReviewService.findAllCommentsByProductId(ProductService.findById(id).get()), HttpStatus.OK);
    }



}
