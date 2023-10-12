/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service;

import com.ou.demo.dto.ReviewDto;
import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.Review;
import com.ou.demo.pojos.User;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface ReviewService {

    Review addComment(Review c, User userId, int proId, int reply);

    Review findCommentById(int id);

    List<Review> findAllCommentsByProductId(Product id);

    boolean deleteComment(Review id);

    List<Review> getAllByCommentId(Review c);


}
