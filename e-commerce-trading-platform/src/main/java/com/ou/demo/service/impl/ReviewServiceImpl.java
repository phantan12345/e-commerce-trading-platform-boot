/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.impl;

import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.Reriew;
import com.ou.demo.pojos.User;
import com.ou.demo.repositories.ReviewRepository;
import com.ou.demo.service.ProductService;
import com.ou.demo.service.ReviewService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ProductService ProductService;

    @Autowired
    private ReviewRepository ReviewRepository;

    @Override
    public Reriew addComment(Reriew r, User userId, int proId, int reply) {
        Product p = ProductService.findById(proId);

        Reriew replyComment = null;

        if (reply != 0) {
            replyComment = ReviewRepository.findById(reply).get();

            r.setReriewId(replyComment);
        }
        r.setUserId(userId);
        r.setProductId(p);
        r.setDateContent(new Date());

        return ReviewRepository.save(r);
    }

    @Override
    public Reriew findCommentById(int id) {
        return ReviewRepository.findById(id).get();
    }

    @Override
    public List<Reriew> findAllCommentsByProductId(Product id) {
        return ReviewRepository.findAll();

    }

    @Override
    public boolean deleteComment(Reriew id) {
        try {
            ReviewRepository.delete(id);
            return true;
        } catch (Exception e) {
            return true;

        }
    }

    @Override
    public Reriew getComment(User user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteComment(int id, int userId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Reriew> getAllByCommentId(Reriew c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Reriew getAllByProductId(Product p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
