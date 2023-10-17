/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.dto.CartDto;
import com.ou.demo.dto.CartInput;
import com.ou.demo.dto.ProdcutDto;
import com.ou.demo.dto.ProductInput;
import com.ou.demo.pojos.Payment;
import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.ProductStore;
import com.ou.demo.pojos.Store;
import com.ou.demo.pojos.User;
import com.ou.demo.service.ProductService;
import com.ou.demo.service.ProductStoreService;
import com.ou.demo.service.StoreService;
import com.ou.demo.service.UserService;
import com.ou.demo.service.receiptService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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

    private receiptService receiptService;

    private ProductService productService;

    private UserService UserService;

    private StoreService storeService;

    @PostMapping("/product/")
    public ResponseEntity<?> addPRoduct(@Valid @RequestParam ProductInput p, @RequestPart List<MultipartFile> file) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = UserService.findByUsername(userDetails.getUsername());

            Store store = storeService.findStoreByUserID(user);

            if (store.getActive() == Boolean.TRUE) {
                ProdcutDto dto = productService.create(p, file, store);
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
            ProductStore ps = ProductStoreService.findByProduct(p);
            CartDto c = new CartDto();
            c.setId(p.getId());
            c.setCount(1);
            if (ps.getVoucherId() == null) {
                c.setVoucher(null);
            } else {
                c.setVoucher(ps.getVoucherId());

            }

            cart.put(productId, c);
        }

        session.setAttribute("cart", cart);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/products/")
    public ResponseEntity<?> getProducts() {
        List<ProdcutDto> dto = productService.findAll();
        if (dto != null) {
            return new ResponseEntity<>(
                    dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(
                "orror find products", HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/product/")
    public ResponseEntity<?> getProducts(@RequestParam int page) {
        return new ResponseEntity<>(productService.page(page), HttpStatus.OK);
    }

    @PostMapping("/pay/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> add(@RequestBody CartInput carts) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User userCuren = UserService.findByUsername(userDetails.getUsername());

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
        return new ResponseEntity<>("no accept", HttpStatus.UNAUTHORIZED);

    }

    @GetMapping("/product/dsc/")
    public ResponseEntity<?> get() {
        return new ResponseEntity<>(productService.findAllByOrderByPriceDesc(), HttpStatus.OK);

    }

    @GetMapping("/product/namedsc/")
    public ResponseEntity<?> getName() {
        return new ResponseEntity<>(productService.findAllByOrderByProductNameDesc(), HttpStatus.OK);
    }

    @GetMapping("search/")
    public ResponseEntity<?> search(@RequestParam Map<String, String> params) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {

            List<Product> list = productService.search(params);
            if (list != null) {
                return ResponseEntity.ok().body(list);
            }
            return new ResponseEntity<>("LIST NULL", HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deletePRoduct(@RequestPart int id) {
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

}
