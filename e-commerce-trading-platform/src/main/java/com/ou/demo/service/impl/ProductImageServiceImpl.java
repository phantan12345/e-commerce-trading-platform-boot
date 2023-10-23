/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.impl;

import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.ProductImage;
import com.ou.demo.pojos.ProductStore;
import com.ou.demo.repositories.ProductImageReponsitory;
import com.ou.demo.service.ProductImageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class ProductImageServiceImpl implements ProductImageService{

    @Autowired
    private ProductImageReponsitory ProductImageReponsitory;
    
    @Override
    public ProductImage create(ProductImage img) {
        return ProductImageReponsitory.save(img);
    }

    @Override
    public List<ProductImage> findByProdctId(Product id) {
        return ProductImageReponsitory.findByProdcutId(id);
    }
    
    
}
