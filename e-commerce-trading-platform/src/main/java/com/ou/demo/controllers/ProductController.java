/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.service.Receipts.DTO.CartInput;
import com.ou.demo.service.Products.DTO.ProductDto;
import com.ou.demo.service.Products.DTO.ProductInput;
import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.User;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.ou.demo.service.Categorys.ICategoryService;
import com.ou.demo.service.Products.IProductService;
import com.ou.demo.service.Receipts.IReceiptService;
import com.ou.demo.service.Users.DTO.CurrentUser;
import com.ou.demo.service.Users.DTO.UsersDto;
import com.ou.demo.service.Users.IUserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ADMIN
 */
@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ProductController {

    private IReceiptService receiptService;

    private IProductService productService;

    private IUserService UserService;

    private ICategoryService CategoryService;

    @PostMapping("/product")
    public ResponseEntity<?> addPRoduct(@CurrentUser UsersDto currentUser, @Valid @RequestParam Map<String, String> params, @RequestPart List<MultipartFile> file) {
        User user = UserService.findById(currentUser.getId());

        ProductDto dto = productService.create(params, file, user);
        if (dto == null) {
            return new ResponseEntity<>("error find products", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(dto, HttpStatus.OK);

        }
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProducts() {
        List<ProductDto> dto;

        dto = productService.findAll();
        return new ResponseEntity<>(
                dto, HttpStatus.OK);

    }

    @GetMapping("/product/{pageSize}/{pageNumber}")
    public ResponseEntity<?> getProducts(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber) {
        return new ResponseEntity<>(productService.page(pageSize, pageNumber), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam Map<String, String> params) {

        List<ProductDto> list = productService.search(params);
        if (list != null) {
            return ResponseEntity.ok().body(list);
        }
        return new ResponseEntity<>("LIST NULL", HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deletePRoduct(@PathVariable int id) {

        Product p = productService.findById(id);

        Product dto = productService.delete(p);

        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    @PutMapping("/product")
    public ResponseEntity<?> updateProduct(@RequestBody ProductInput dto) {

        Product p = productService.findById(dto.getId());

        p.setProductName(dto.getProductName());
        p.setCategoryId(CategoryService.findCateById(dto.getCategoryId().getId()));
        p.setPrice(dto.getPrice());
        p.setCount(dto.getCount());
        return new ResponseEntity<>(productService.update(p), HttpStatus.OK);

    }

    @PostMapping("/product/stat")
    public ResponseEntity<?> stat() {

        ProductDto dto = null;
        if (dto == null) {
            return new ResponseEntity<>("error find products", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(dto, HttpStatus.OK);

        }
    }

}
