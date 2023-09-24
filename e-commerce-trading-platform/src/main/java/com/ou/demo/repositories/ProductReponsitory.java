/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.repositories;

import com.ou.demo.pojos.Category;
import com.ou.demo.pojos.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADMIN
 */
@Repository
@Transactional
public interface ProductReponsitory extends JpaRepository<Product, Integer> {

    List<Product> findAllByOrderByPriceDesc();

    List<Product> findAllByOrderByProductNameDesc();


    @Query(value = "SELECT p FROM Product p WHERE (:productName IS NULL OR p.productName LIKE %:productName%) "
            + "AND (:categoryName IS NULL OR p.categoryId = :categoryName)"
            + " AND (p.price IS NULL OR p.price BETWEEN :minPrice AND :maxPrice)")
    List<Product> searchProducts(@Param("productName") String productName, @Param("categoryName") Category categoryName, @Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice);

}
