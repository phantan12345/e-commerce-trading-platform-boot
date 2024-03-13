/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.service.OrderDetails.DTO.DateDto;
import com.ou.demo.service.Products.DTO.ProductDto;
import com.ou.demo.service.ProductStores.DTO.ProductStoreDto;
import com.ou.demo.service.Vouchers.DTO.VoucherDto;
import com.ou.demo.pojos.ProductStore;
import com.ou.demo.pojos.Store;
import com.ou.demo.pojos.User;
import com.ou.demo.pojos.Voucher;
import com.ou.demo.service.ProductStores.ProductStoreService;
import com.ou.demo.service.Vouchers.VoucherService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ou.demo.service.Products.IProductService;
import com.ou.demo.service.Orders.IOrderService;
import com.ou.demo.service.Users.DTO.CurrentUser;
import com.ou.demo.service.Users.DTO.UsersDto;
import com.ou.demo.service.Users.IUserService;
import com.ou.demo.service.Stores.IStoreService;

/**
 *
 * @author ADMIN
 */
@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductStoreController {

    private ProductStoreService ProductStoreService;

    private IUserService UserService;

    private IStoreService StoreService;

    private VoucherService VoucherService;

    private IOrderService OrderService;

    private IProductService ProductService;

    @GetMapping("/product-store")
    public ResponseEntity<?> getProduct(@CurrentUser UsersDto currentUser) {
        User user = UserService.findById(currentUser.getId());
        Store s = StoreService.findStoreByUserID(user);
        List<ProductDto> dto = ProductStoreService.findAllByStore(s);
        if (dto == null) {
            return new ResponseEntity<>("orror find products",
                    HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(
                    dto, HttpStatus.OK);
        }

    }

    @GetMapping("/product-store/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        ProductStoreDto dto = ProductStoreService.getDto(ProductService.findById(id));
        if (dto == null) {
            return new ResponseEntity<>("orror find products",
                    HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(
                    dto, HttpStatus.OK);
        }
    }

    @GetMapping("/stat")
    public ResponseEntity<?> getStat(@CurrentUser UsersDto currentUser, @RequestBody DateDto dto) {
        User user = UserService.findById(currentUser.getId());
        Store s = StoreService.findStoreByUserID(user);
        return ResponseEntity.ok().body(OrderService.stat(s, dto));

    }

    @DeleteMapping("/product-store/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {

        ProductStore ps = ProductStoreService.findById(id);
        return ResponseEntity.ok().body(ProductService.delete(ps.getProductId()));

    }

}
