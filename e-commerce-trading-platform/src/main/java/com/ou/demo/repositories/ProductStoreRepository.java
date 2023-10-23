/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.repositories;

import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.ProductStore;
import com.ou.demo.pojos.Store;
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
public interface ProductStoreRepository extends JpaRepository<ProductStore, Integer> {

    @Query("SELECT p FROM ProductStore p WHERE p.productId = ?1")
    ProductStore findByProduct(Product id);

    @Query("SELECT p FROM ProductStore p WHERE p.storeId = ?1")
    List<ProductStore> findByStore(Store id);

    ProductStore findByproductId(Store id);

}
