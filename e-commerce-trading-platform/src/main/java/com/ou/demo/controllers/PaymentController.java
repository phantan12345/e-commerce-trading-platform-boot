/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.service.Payment.IPaymentService;
import com.ou.demo.service.Users.DTO.CurrentUser;
import com.ou.demo.service.Users.DTO.UsersDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class PaymentController {
    
    private IPaymentService paymentService;

    @GetMapping("/payments")
    public ResponseEntity<?> getAllByCurrentUser(@CurrentUser UsersDto user) {
        return new ResponseEntity<>(paymentService.getAll(), HttpStatus.OK);
    }
}
