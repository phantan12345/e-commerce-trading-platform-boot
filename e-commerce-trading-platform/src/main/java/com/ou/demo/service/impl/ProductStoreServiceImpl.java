/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.impl;

import com.ou.demo.dto.ProdcutDto;
import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.ProductImage;
import com.ou.demo.pojos.ProductStore;
import com.ou.demo.pojos.Store;
import com.ou.demo.repositories.ProductStoreRepository;
import com.ou.demo.service.ProductImageService;
import com.ou.demo.service.ProductService;
import com.ou.demo.service.ProductStoreService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class ProductStoreServiceImpl implements ProductStoreService {

    @Autowired
    private ProductStoreRepository ProductStoreRepository;

    @Autowired
    private ProductImageService ProductImageService;

    @Override
    public ProductStore create(ProductStore ps) {
        return ProductStoreRepository.save(ps);
    }

    @Override
    public ProductStore findByProduct(Product id) {
        return ProductStoreRepository.findByProduct(id);
    }

    @Override
    public List<ProdcutDto> findAllByStore(Store id) {
        List<ProductStore> list = ProductStoreRepository.findByStore(id);

        List<ProdcutDto> listDto = new ArrayList<>();

        for (ProductStore product : list) {
            List<String> img = ProductImageService.findByProdctId(product.getProductId())
                    .stream()
                    .map(ProductImage::getUrl)
                    .collect(Collectors.toList());
            ProdcutDto dto = ProdcutDto.builder().id(product.getId())
                    .productName(product.getProductId().getProductName())
                    .price(product.getProductId().getPrice())
                    .categoryId(product.getProductId().getCategoryId())
                    .productImage(img).build();
            listDto.add(dto);

        }
        return listDto;
    }

}
