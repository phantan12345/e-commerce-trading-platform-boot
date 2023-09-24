/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.dto.DateDto;
import com.ou.demo.dto.ProdcutDto;
import com.ou.demo.dto.VoucherDto;
import com.ou.demo.pojos.ProductStore;
import com.ou.demo.pojos.Store;
import com.ou.demo.pojos.User;
import com.ou.demo.pojos.Voucher;
import com.ou.demo.service.OrderService;
import com.ou.demo.service.ProductService;
import com.ou.demo.service.ProductStoreService;
import com.ou.demo.service.StoreService;
import com.ou.demo.service.UserService;
import com.ou.demo.service.VoucherService;
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

    private UserService UserService;

    private StoreService StoreService;

    private VoucherService VoucherService;

    private OrderService OrderService;

    private ProductService ProductService;

    @GetMapping("product-store/")
    public ResponseEntity<?> getProduct() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = UserService.findByUsername(userDetails.getUsername());
            Store s = StoreService.findStoreByUserID(user);
            List<ProdcutDto> dto = ProductStoreService.findAllByStore(s);
            return new ResponseEntity<>(dto == null ? "orror find products"
                    : new ResponseEntity(dto, HttpStatus.OK), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("no accept", HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("product-store/{id}")
    public ResponseEntity<?> addVou(@RequestBody VoucherDto dto, @PathVariable("id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {

            Voucher vou = VoucherService.findByid(dto.getId());

            ProductStore ps = ProductStoreService.findById(id);
            ps.setVoucherId(vou);
            return ResponseEntity.ok().body(ProductStoreService.create(ps));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("stat/")
    public ResponseEntity<?> getStat(@RequestBody DateDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = UserService.findByUsername(userDetails.getUsername());

            Store s = StoreService.findStoreByUserID(user);
            return ResponseEntity.ok().body(OrderService.findOrderByStore(s, dto));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("product-store/{id}")
    public ResponseEntity<?> delete( @PathVariable("id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {

            ProductStore ps = ProductStoreService.findById(id);
            return ResponseEntity.ok().body(ProductService.delete(ps.getProductId()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
