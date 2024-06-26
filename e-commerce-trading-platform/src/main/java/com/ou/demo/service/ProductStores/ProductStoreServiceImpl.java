/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.ProductStores;

import com.ou.demo.service.Products.DTO.ProductDto;
import com.ou.demo.service.ProductStores.DTO.ProductStoreDto;
import com.ou.demo.pojos.Order1;
import com.ou.demo.pojos.Orderdetail;
import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.ProductImage;
import com.ou.demo.pojos.ProductStore;
import com.ou.demo.pojos.Store;
import com.ou.demo.repositories.ProductStoreRepository;
import com.ou.demo.service.ProductImages.ProductImageService;
import com.ou.demo.service.ProductStores.ProductStoreService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ou.demo.service.OrderDetails.IOrderdetailService;
import com.ou.demo.service.Products.DTO.ProductSumary;
import com.ou.demo.service.Products.IProductService;

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
    
    @Autowired
    private ModelMapper ModelMapper;
    
    @Override
    public ProductStore create(ProductStore ps) {
        return ProductStoreRepository.save(ps);
    }
    
    @Override
    public ProductStore findByProduct(Product id) {
        return ProductStoreRepository.findByProduct(id);
    }
    
    @Override
    public ProductStoreDto findAllByStore(Store id) {
        List<Object[]> list = ProductStoreRepository.findListProductByStore(id);
        List<ProductSumary> listProduct = new ArrayList<>();
        list.stream().map(mapper -> listProduct.add( new ProductSumary((Product) mapper[0], 
                Integer.valueOf(mapper[1].toString())))).toArray();
        
        ProductStoreDto listDto = ProductStoreDto.builder()
                .store(id)
                .products(listProduct)
                .build();
        
        return listDto;
    }
    
    @Override
    public ProductStore findById(int id) {
        return ProductStoreRepository.findById(id).get();
    }
    
    @Override
    public Object stat() {
        
        Order1 or = new Order1();
        Set<Orderdetail> orderDetails = or.getOrderdetailSet();
        return orderDetails;
    }
    
}
