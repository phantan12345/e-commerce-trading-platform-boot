/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.pojos.Reriew;
import com.ou.demo.pojos.User;
import com.ou.demo.service.ReviewService;
import com.ou.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ReviewController {

    private ReviewService ReviewService;

    private UserService UserService;

    @PostMapping("/product/{id}/comment")
    public ResponseEntity<?> createCommentToProduct(@RequestBody Reriew c, @PathVariable("id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User currentUser = UserService.findByUsername(userDetails.getUsername());
            Reriew Reriew = ReviewService.addComment(c, currentUser, id, 0);
            return new ResponseEntity<>(Reriew, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    //reply comment
    @PostMapping("/product/{Proid}/comment/{id}/comments")
    public ResponseEntity<?> replyToComent(@RequestBody Reriew c, @PathVariable("Proid") int proId, @PathVariable("id") int id) {
        System.out.println(proId);
        System.out.println(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User currentUser = UserService.findByUsername(userDetails.getUsername());
            Reriew Reriew = ReviewService.addComment(c, currentUser, proId, id);
            return new ResponseEntity<>(Reriew == null ? new ResponseEntity<>("You do not have permission to update this comment", HttpStatus.UNAUTHORIZED) : Reriew, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
