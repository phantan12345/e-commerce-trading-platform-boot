/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.ProductStores;

import com.ou.demo.service.Products.DTO.ProductDto;
import com.ou.demo.service.ProductStores.DTO.ProductStoreDto;
import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.ProductStore;
import com.ou.demo.pojos.Store;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
public interface ProductStoreService {

    ProductStore create(ProductStore ps);

    ProductStore findByProduct(Product id);


    ProductStoreDto findAllByStore(Store s);

    ProductStore findById(int id);


    Object stat();

}
