/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.repositories;

import com.ou.demo.pojos.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
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

}
