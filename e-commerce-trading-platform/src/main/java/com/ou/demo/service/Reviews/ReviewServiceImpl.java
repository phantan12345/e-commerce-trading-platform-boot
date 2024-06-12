/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Reviews;

import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.Review;
import com.ou.demo.pojos.User;
import com.ou.demo.repositories.ProductReponsitory;
import com.ou.demo.repositories.ReviewRepository;
import com.ou.demo.service.Reviews.ReviewService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ou.demo.service.Products.IProductService;
import com.ou.demo.service.Reviews.DTO.ReviewDto;
import java.util.ArrayList;
import org.modelmapper.ModelMapper;

/**
 *
 * @author ADMIN
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ProductReponsitory ProductService;

    @Autowired
    private ReviewRepository ReviewRepository;

    @Autowired
    private ModelMapper ModelMapper;

    @Override
    public Review addComment(Review r, User userId, int proId, int reply) {
        Product p = ProductService.findById(proId).get();

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
        if (optionalReview != null) {
            return optionalReview;
        }
        return null;
    }

    @Override
    public List<ReviewDto> findAllCommentsByProductId(Product id) {
        List<ReviewDto> listDto = new ArrayList<>();
        List<Review> listReview = ReviewRepository.findByProductId(id);
        listReview.forEach(mapper -> listDto.add(coverReview(mapper)));

        return listDto;

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

    private ReviewDto coverReview(Review r) {

        ReviewDto dto = ModelMapper.map(r, ReviewDto.class);
        dto.setReply(ReviewRepository.findByreviewId(r));
        return dto;
    }
}
