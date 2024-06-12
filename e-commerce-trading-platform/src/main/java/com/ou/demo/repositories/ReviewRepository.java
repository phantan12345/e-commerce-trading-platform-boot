/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.repositories;

import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADMIN
 */
@Repository
@Transactional
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("SELECT r FROM Review r WHERE r.id = ?1")
    Review findById(int id);

    List<Review> findByreviewId(Review id);

    @Query("SELECT r FROM Review r WHERE r.productId = ?1")
    List<Review>  findByProductId(Product id);
    
    
     @Query("SELECT r FROM Review r WHERE r.reviewId = ?1")
    List<Review>  findByReviewId(Review id);
}
