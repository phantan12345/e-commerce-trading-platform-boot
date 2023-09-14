/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.impl;

import com.ou.demo.pojos.ProductStore;
import com.ou.demo.repositories.ProductStoreRepository;
import com.ou.demo.service.ProductStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class ProductStoreServiceImpl implements ProductStoreService{

    @Autowired
    private ProductStoreRepository ProductStoreRepository;
    
    @Override
    public ProductStore create(ProductStore ps) {
        return ProductStoreRepository.save(ps);
    }
    
}
