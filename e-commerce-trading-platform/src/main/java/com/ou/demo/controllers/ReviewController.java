/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.dto.ReviewDto;
import com.ou.demo.pojos.ProductStore;
import com.ou.demo.pojos.Review;
import com.ou.demo.pojos.User;
import com.ou.demo.service.ProductService;
import com.ou.demo.service.ReviewService;
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
public class ReviewController {

    private ReviewService ReviewService;

    private UserService UserService;

    private ProductService ProductService;

    @PostMapping("/product/{id}/comment")
    public ResponseEntity<?> createCommentToProduct(@RequestBody Review c, @PathVariable("id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User currentUser = UserService.findByUsername(userDetails.getUsername());
            Review Reriew = ReviewService.addComment(c, currentUser, id, 0);

            return new ResponseEntity<>(Reriew == null ? "orror find products"
                    : new ResponseEntity(Reriew, HttpStatus.OK), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("no accept", HttpStatus.UNAUTHORIZED);
        }
    }

    //reply comment
    @PostMapping("/product/{Proid}/comment/{id}/comments")
    public ResponseEntity<?> replyToComent(@RequestBody Review c, @PathVariable("Proid") int proId, @PathVariable("id") int id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User currentUser = UserService.findByUsername(userDetails.getUsername());
            Review Reriew = ReviewService.addComment(c, currentUser, proId, id);
            return new ResponseEntity<>(Reriew == null ? new ResponseEntity<>("You do not have permission to update this comment", HttpStatus.BAD_REQUEST) : Reriew, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/product/{id}/comment")
    public ResponseEntity<List<?>> getAllCommentsByProduct(@PathVariable("id") int id) {
        return new ResponseEntity<>(ReviewService.findAllCommentsByProductId(ProductService.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<?> replyToComentAll(@PathVariable("id") int id) {
        List<Review> dto = this.ReviewService.getAllByCommentId(ReviewService.findCommentById(id));

        if (dto != null) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
