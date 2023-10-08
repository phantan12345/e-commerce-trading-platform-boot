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
import com.ou.demo.util.GenericSpecifications;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
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

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ProdcutDto create(Map<String, String> params, List<MultipartFile> file, Store store) {
        Product p = new Product();

        p.setProductName(params.get("productName"));
        BigDecimal price = new BigDecimal(params.get("price"));
        p.setPrice(price);
        p.setCategoryId(CategoryService.findCateById(Integer.parseInt(params.get("cateid"))));
        p.setActive(Boolean.TRUE);
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

    @Override
    public List<ProdcutDto> findAll() {
        List<Product> list = productReponsitory.findAll();

        List<ProdcutDto> listDto = new ArrayList<>();

        for (Product product : list) {
            if (product.getActive() != Boolean.FALSE) {
                List<String> img = ProductImageService.findByProdctId(product)
                        .stream()
                        .map(ProductImage::getUrl)
                        .collect(Collectors.toList());

                ProdcutDto dto = ProdcutDto.builder().id(product.getId())
                        .productName(product.getProductName())
                        .price(product.getPrice())
                        .categoryId(product.getCategoryId())
                        .productImage(img).build();
                listDto.add(dto);
            }
        }
        return listDto;
    }

    @Override
    public Page<Product> page(int page) {
        Pageable p = PageRequest.of(page, 5, Sort.by("productName").descending());
        return productReponsitory.findAll(p);
    }

    @Override
    public List<Product> findAllByOrderByPriceDesc() {
        return productReponsitory.findAllByOrderByPriceDesc();
    }

    @Override
    public List<Product> findAllByOrderByProductNameDesc() {
        return productReponsitory.findAllByOrderByProductNameDesc();
    }

    @Override
    public List<Product> search(Map<String, String> params) {
        List<Specification<Product>> list = new ArrayList<>();

        String kw = params.get("kw");
        if (kw != null && !kw.isEmpty()) {
            Specification<Product> spec = GenericSpecifications.fieldContains("productName", kw);
            list.add(spec);

        }

        String cate = params.get("cate");
        if (cate != null && !cate.isEmpty()) {
            Specification<Product> spec = GenericSpecifications.fieldEquals("categoryId", cate);
            list.add(spec);

        }

        String from = params.get("from");
        if (from != null && !from.isEmpty()) {
            Specification<Product> spec = GenericSpecifications.hasLess("price", from);

            list.add(spec);

        }
        String to = params.get("to");
        if (to != null && !to.isEmpty()) {
            Specification<Product> spec = GenericSpecifications.hasThan("price", to);
            list.add(spec);

        }

        return productReponsitory.findAll(GenericSpecifications.createSpecification(list));

    }

    @Override
    public Product delete(Product product) {
        product.setActive(Boolean.FALSE);
        return productReponsitory.save(product);
    }

    @Override
    public Product update(Product ps) {
        return productReponsitory.save(ps);
    }

}
