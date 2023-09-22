/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.pojos.Store;
import com.ou.demo.pojos.User;
import com.ou.demo.pojos.Voucher;
import com.ou.demo.service.VoucherService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@AllArgsConstructor
@RequestMapping("/api")
@RestController
@CrossOrigin
public class VoucherController {

    private VoucherService VoucherService;

    @PostMapping("voucher/")
    public ResponseEntity<?> createStore(@RequestBody Voucher vou) {

        if (vou != null) {
            return ResponseEntity.ok().body(VoucherService.create(vou));

        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("voucher/")
    public String getStore(@RequestBody Voucher vou) {

            return "rfe" ;

       
    }

}
