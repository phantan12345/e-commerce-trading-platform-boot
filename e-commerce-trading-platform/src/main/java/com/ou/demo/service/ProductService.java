/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service;

import com.ou.demo.dto.ProdcutDto;
import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.ProductStore;
import com.ou.demo.pojos.Store;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ADMIN
 */
public interface ProductService {

    ProdcutDto create(Map<String, String> params, List<MultipartFile> file, Store store);

    Product findById(int id);

    List<ProdcutDto> findAll();

    Page<Product> page(Pageable p);

    List<Product> findAllByOrderByPriceDesc();

    List<Product> findAllByOrderByProductNameDesc();


}
