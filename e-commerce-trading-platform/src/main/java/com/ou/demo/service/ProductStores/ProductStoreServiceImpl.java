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
    public List<ProductDto> findAllByStore(Store id) {
        List<ProductStore> list = ProductStoreRepository.findByStore(id);

        List<ProductDto> listDto = new ArrayList<>();

        for (ProductStore product : list) {
            Set<ProductImage> img = ProductImageService.findByProdctId(product.getProductId());
            
            ProductDto dto = ProductDto.builder().id(product.getId())
                    .productName(product.getProductId().getProductName())
                    .price(product.getProductId().getPrice())
                    .categoryId(product.getProductId().getCategoryId())
                    .productImageSet(img).build();
            listDto.add(dto);

        }
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

    @Override
    public ProductStore findlByStore(Store s) {
        return ProductStoreRepository.findByproductId(s);
    }

    @Override
    public ProductStoreDto getDto(Product id) {
        ProductStore ps = ProductStoreRepository.findByProduct(id);
        List<String> productImageUrls = new ArrayList<>();
        ps.getProductId().getProductImageSet().forEach(p -> productImageUrls.add(p.getUrl()));
        return ProductStoreDto.builder()
                .price(ps.getProductId().getPrice())
                .productName(ps.getProductId().getProductName())
                .productImage(productImageUrls)
                .categoryId(ps.getProductId().getCategoryId())
                .store(ps.getStoreId())
                .build();

    }

}
