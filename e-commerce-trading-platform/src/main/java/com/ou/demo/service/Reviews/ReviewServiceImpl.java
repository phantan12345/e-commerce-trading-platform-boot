/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Reviews;

import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.Review;
import com.ou.demo.pojos.User;
import com.ou.demo.repositories.ReviewRepository;
import com.ou.demo.service.Reviews.ReviewService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ou.demo.service.Products.IProductService;

/**
 *
 * @author ADMIN
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private IProductService ProductService;

    @Autowired
    private ReviewRepository ReviewRepository;

    @Override
    public Review addComment(Review r, User userId, int proId, int reply) {
        Product p = ProductService.findById(proId);

        Review replyComment = null;

        if (reply != 0) {
            replyComment = this.findCommentById(reply);

            r.setReviewId(replyComment);
        }
        r.setUserId(userId);
        r.setProductId(p);
        r.setDateContent(new Date());

        return ReviewRepository.save(r);
    }

    @Override
    public Review findCommentById(int id) {
        Review optionalReview = ReviewRepository.findById(id);
        if (optionalReview!=null) {
            return optionalReview;
        }
        return null;
    }

    @Override
    public List<Review> findAllCommentsByProductId(Product id) {
        return ReviewRepository.findAll();

    }

    @Override
    public boolean deleteComment(Review id) {
        try {
            ReviewRepository.delete(id);
            return true;
        } catch (Exception e) {
            return true;

        }
    }

    @Override
    public List<Review> getAllByCommentId(Review c) {
        return ReviewRepository.findByreviewId(c);
    }

}
