/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.service.Receipts.DTO.CartInput;
import com.ou.demo.service.Products.DTO.ProductDto;
import com.ou.demo.service.Products.DTO.ProductInput;
import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.Store;
import com.ou.demo.pojos.User;
import com.ou.demo.service.ProductStores.ProductStoreService;
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
import com.ou.demo.service.Stores.IStoreService;
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

    private ProductStoreService ProductStoreService;

    private IReceiptService receiptService;

    private IProductService productService;

    private IUserService UserService;

    private IStoreService storeService;

    private ICategoryService CategoryService;

    @PostMapping("/product")
    public ResponseEntity<?> addPRoduct(@CurrentUser UsersDto currentUser, @Valid @RequestParam Map<String, String> params, @RequestPart List<MultipartFile> file) {
        User user = UserService.findById(currentUser.getId());

        Store store = storeService.findStoreByUserID(user);

        if (store.getIsDelete() == Boolean.FALSE) {
            ProductDto dto = productService.create(params, file, store);
            if (dto == null) {
                return new ResponseEntity<>("error find products", HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(dto, HttpStatus.OK);

            }
        } else {
            return new ResponseEntity<>("error add products", HttpStatus.FORBIDDEN);

        }

    }

    @GetMapping("/products")
    public ResponseEntity<?> getProducts(@CurrentUser UsersDto currenUser) {
        User user = UserService.findById(currenUser.getId());
        List<ProductDto> dto = productService.findAll(user);
        if (dto != null) {
            return new ResponseEntity<>(
                    dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(
                "orror find products", HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/product")
    public ResponseEntity<?> getProducts(@RequestParam int page) {
        return new ResponseEntity<>(productService.page(page), HttpStatus.OK);
    }

    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> add(@CurrentUser UsersDto currentUser, @RequestBody CartInput carts) {

        User userCuren = UserService.findById(currentUser.getId());

        Object cart = this.receiptService.addReceipt(carts, userCuren);
        if (cart == null) {
            return new ResponseEntity<>("ERROR PAYMENT METHOD ",
                    HttpStatus.BAD_REQUEST
            );
        } else {

            return new ResponseEntity<>(
                    cart, HttpStatus.OK);
        }

    }

    @GetMapping("/product/dsc")
    public ResponseEntity<?> get() {
        return new ResponseEntity<>(productService.findAllByOrderByPriceDesc(), HttpStatus.OK);

    }

    @GetMapping("/product/namedsc")
    public ResponseEntity<?> getName() {
        return new ResponseEntity<>(productService.findAllByOrderByProductNameDesc(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam Map<String, String> params) {

        List<Product> list = productService.search(params);
        if (list != null) {
            return ResponseEntity.ok().body(list);
        }
        return new ResponseEntity<>("LIST NULL", HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deletePRoduct(@PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            Product p = productService.findById(id);

            if (p != null) {
                Product dto = productService.delete(p);
                if (dto == null) {
                    return new ResponseEntity<>("error find products", HttpStatus.BAD_REQUEST);
                } else {
                    return new ResponseEntity<>(dto, HttpStatus.OK);

                }
            } else {
                return new ResponseEntity<>("error add products", HttpStatus.FORBIDDEN);

            }

        } else {
            return new ResponseEntity<>("no accept", HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductInput dto, @PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            Product p = productService.findById(id);
            if (p == null) {

                return new ResponseEntity<>("error find products", HttpStatus.BAD_REQUEST);
            } else {
                p.setProductName(dto.getProductName());
                p.setIsDelete(Boolean.FALSE);
                p.setCategoryId(CategoryService.findCateById(dto.getCateId()));
                p.setPrice(dto.getPrice());
                return new ResponseEntity<>(productService.update(p), HttpStatus.OK);

            }
        } else {
            return new ResponseEntity<>("error add products", HttpStatus.FORBIDDEN);

        }

    }

}
