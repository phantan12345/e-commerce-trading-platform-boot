/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Products;

import com.ou.demo.pojos.Order1;
import com.ou.demo.pojos.Orderdetail;
import com.ou.demo.service.Images.ImageServiceImpl;
import com.ou.demo.service.Products.DTO.PageDto;
import com.ou.demo.service.Products.DTO.ProductDto;
import com.ou.demo.service.Products.DTO.ProductInput;
import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.ProductImage;
import com.ou.demo.pojos.ProductStore;
import com.ou.demo.pojos.Store;
import com.ou.demo.pojos.User;
import com.ou.demo.repositories.OrderReponsitory;
import com.ou.demo.repositories.OrderdetailRepository;
import com.ou.demo.repositories.ProductReponsitory;
import com.ou.demo.service.ProductImages.ProductImageService;
import com.ou.demo.service.ProductStores.ProductStoreService;
import com.ou.demo.util.GenericSpecifications;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.ou.demo.service.Categorys.ICategoryService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageImpl;

/**
 *
 * @author ADMIN
 */
@Service
public class ProductService implements IProductService {

    @Autowired
    private ModelMapper ModelMapper;

    @Autowired
    private ProductReponsitory productReponsitory;

    @Autowired
    private ICategoryService CategoryService;

    @Autowired
    private ImageServiceImpl ImageService;

    @Autowired
    private ProductStoreService ProductStoreService;

    @Autowired
    private ProductImageService ProductImageService;

    @Autowired
    private OrderReponsitory orderReponsitory;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDto create(Map<String, String> params, List<MultipartFile> file, Store store) {
        Product p = new Product();

        p.setProductName(params.get("productName"));
        BigDecimal price = new BigDecimal(params.get("price"));
        p.setPrice(price);
        p.setCategoryId(CategoryService.findCateById(Integer.parseInt(params.get("cateid"))));
        p.setDelete(Boolean.FALSE);
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
        return ModelMapper.map(p, ProductDto.class);
    }

    @Override
    public Product findById(int id) {

        return productReponsitory.findById(id).get();
    }

    @Override
    public List<ProductDto> findAll(User users) {

        List<Product> listAllProduct = productReponsitory.findAll();
        List<Product> listHistoryProduct = productReponsitory.findHistoryProduct(users.getId());
        listAllProduct.addAll(listHistoryProduct);
        return listAllProduct.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }

    @Override
    public Page<Product> page(int pageSize, int pageNumber) {

        Pageable paging = PageRequest.of(pageNumber, pageSize);
        
        Page<Product> pageDto=productReponsitory.findAll(paging);
        return pageDto;
    }

    @Override
    public List<ProductDto> findAllByOrderByPriceDesc() {

        return productReponsitory.findAllByOrderByPriceDesc().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findAllByOrderByProductNameDesc() {
        return productReponsitory.findAllByOrderByProductNameDesc().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> search(Map<String, String> params) {
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
            Specification<Product> spec = GenericSpecifications.hasThan("price", from);

            list.add(spec);

        }
        String to = params.get("to");
        if (to != null && !to.isEmpty()) {
            Specification<Product> spec = GenericSpecifications.hasLess("price", to);
            list.add(spec);

        }

        return productReponsitory.findAll(GenericSpecifications.createSpecification(list)).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }

    @Override
    public Product delete(Product product) {
        product.setDelete(Boolean.TRUE);

        return productReponsitory.save(product);
    }

    @Override
    public Product update(Product p) {

        return productReponsitory.save(p);
    }

    private ProductDto convertToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public List<ProductDto> findAll() {

        return productReponsitory.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
