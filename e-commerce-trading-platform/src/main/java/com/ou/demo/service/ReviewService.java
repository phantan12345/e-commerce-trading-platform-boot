/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service;

import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.Reriew;
import com.ou.demo.pojos.User;
import java.util.List;

/**
 *
 * @author ADMIN
 */

public interface ReviewService {

    Reriew addComment(Reriew c, User userId, int proId, int reply);

    Reriew findCommentById(int id);

    List<Reriew> findAllCommentsByProductId(Product id);

    boolean deleteComment(Reriew id);

    Reriew getComment(User user);

    boolean deleteComment(int id, int userId);

    List<Reriew> getAllByCommentId(Reriew c);

    public Reriew getAllByProductId(Product p);
}
