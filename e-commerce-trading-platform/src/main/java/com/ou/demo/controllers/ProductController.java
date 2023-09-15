/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.dto.CartDto;
import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.Store;
import com.ou.demo.pojos.User;
import com.ou.demo.service.ProductService;
import com.ou.demo.service.StoreService;
import com.ou.demo.service.UserService;
import com.ou.demo.service.receiptService;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ADMIN
 */
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private receiptService receiptService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService UserService;

    @Autowired
    private StoreService storeService;

    @PostMapping("/product/")
    public ResponseEntity<?> addPRoduct(@RequestParam Map<String, String> params, @RequestPart List<MultipartFile> file) {
        System.out.println(file);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = UserService.findByUsername(userDetails.getUsername());

            Store store = storeService.findStoreByUserID(user);
            return ResponseEntity.ok().body(productService.create(params, file, store));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/cart/{productId}/")
    @CrossOrigin
    public ResponseEntity<?> cart(@PathVariable(value = "productId") Integer productId, HttpSession session) {
        Map<Integer, CartDto> cart = (Map<Integer, CartDto>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }
        if (cart.containsKey(productId) == true) {
            CartDto c = cart.get(productId);
            c.setCount(c.getCount() + 1);
        } else {
            Product p = productService.findById(productId);
            CartDto c = new CartDto();
            c.setId(p.getId());
            c.setPrice(p.getPrice());
            c.setCount(1);
            cart.put(productId, c);
        }

        session.setAttribute("cart", cart);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/pay/")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    public ResponseEntity<?> add(HttpSession s) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User userCuren = UserService.findByUsername(userDetails.getUsername());
            Map<Integer, CartDto> carts=(Map<Integer, CartDto>) s.getAttribute("cart");
            return new ResponseEntity<>(this.receiptService.addReceipt(carts, userCuren), HttpStatus.OK);
        }
        return new ResponseEntity<>("loi ko them dc cart", HttpStatus.UNAUTHORIZED);

    }

}
