/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service;

import com.ou.demo.dto.ProdcutDto;
import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.ProductStore;
import com.ou.demo.pojos.Store;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface ProductStoreService {

    ProductStore create(ProductStore ps);

    ProductStore findByProduct(Product id);

    List<ProdcutDto> findAllByStore(Store s);

}
