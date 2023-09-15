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
import com.ou.demo.repositories.ProductReponsitory;
import com.ou.demo.service.CategoryService;
import com.ou.demo.service.ProductImageService;
import com.ou.demo.service.ProductService;
import com.ou.demo.service.ProductStoreService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ADMIN
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ModelMapper ModelMapper;

    @Autowired
    private ProductReponsitory productReponsitory;

    @Autowired
    private CategoryService CategoryService;

    @Autowired
    private ImageServiceImpl ImageService;

    @Autowired
    private ProductStoreService ProductStoreService;

    @Autowired
    private ProductImageService ProductImageService;

    @Override
    public ProdcutDto create(@RequestParam Map<String, String> params, List<MultipartFile> file, Store store) {
        Product p = new Product();

        p.setProductName(params.get("productName"));
        BigDecimal price = new BigDecimal(params.get("price"));
        p.setPrice(price);
        p.setCategoryId(CategoryService.findCateByName(params.get("category")));

        ProductStore ps = new ProductStore();
        ps.setCount(Integer.parseInt(params.get("count")));
        ps.setStoreId(store);
        ps.setProductId(p);
        productReponsitory.save(p);
        ProductStoreService.create(ps);
        
        for (MultipartFile f : file) {
            ProductImage img = new ProductImage();
            img.setUrl(ImageService.Cloudinary(f).get("secure_url").toString());
            img.setProductId(p);
            ProductImageService.create(img);

        }
        return ModelMapper.map(p, ProdcutDto.class);
    }

    @Override
    public Product findById(int id) {
        return productReponsitory.findById(id).get();
    }

}
