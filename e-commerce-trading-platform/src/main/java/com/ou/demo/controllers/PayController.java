/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.pojos.User;
import com.ou.demo.service.Categorys.ICategoryService;
import com.ou.demo.service.Products.IProductService;
import com.ou.demo.service.Receipts.DTO.CartInput;
import com.ou.demo.service.Receipts.IReceiptService;
import com.ou.demo.service.Users.DTO.CurrentUser;
import com.ou.demo.service.Users.DTO.UsersDto;
import com.ou.demo.service.Users.IUserService;
import com.ou.demo.service.VNPlay.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/pay")
public class PayController {

    private IReceiptService receiptService;

    private IUserService UserService;

    private VNPayService VNPayService;

    @PostMapping("/delivery")
    public ResponseEntity<?> delivery(@CurrentUser UsersDto currentUser, @RequestBody CartInput carts) {

        User userCuren = UserService.findById(currentUser.getId());
        carts.setUser(userCuren);
        Object cart = this.receiptService.addReceipt(carts);
        if (cart == null) {
            return new ResponseEntity<>("ERROR PAYMENT METHOD ",
                    HttpStatus.BAD_REQUEST
            );
        } else {

            return new ResponseEntity<>(
                    cart, HttpStatus.OK);
        }

    }

    @PostMapping("/VNPay")
    public ResponseEntity<?> VNPay(HttpServletRequest req,@CurrentUser UsersDto currentUser, @RequestBody CartInput carts) throws UnsupportedEncodingException {

        User userCuren = UserService.findById(currentUser.getId());
        carts.setUser(userCuren);
        Object cart = this.VNPayService.createOrder(req,carts);
        if (cart == null) {
            return new ResponseEntity<>("ERROR PAYMENT METHOD ",
                    HttpStatus.BAD_REQUEST
            );
        } else {

            return new ResponseEntity<>(
                    cart, HttpStatus.OK);
        }

    }

    @PostMapping("/return")
    public ResponseEntity<?> VNPayReturn(HttpServletRequest request) {

        Map<String, String> params = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);
            params.put(paramName, paramValue);
        }
        return new ResponseEntity<>(
                params, HttpStatus.OK);
    }

    @GetMapping("/refund")
    public ResponseEntity<?> refund(HttpServletRequest req,@RequestParam Map<String, String> params) throws Exception {

        return new ResponseEntity<>(
                VNPayService.refund(req,params), HttpStatus.OK);

    }
}
