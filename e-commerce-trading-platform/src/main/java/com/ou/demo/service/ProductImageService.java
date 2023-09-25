/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service;

import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.ProductImage;
import com.ou.demo.pojos.ProductStore;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface ProductImageService {

    ProductImage create(ProductImage img);

    List<ProductImage> findByProdctId(Product id);

}
