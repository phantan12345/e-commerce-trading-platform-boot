/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.pojos.Voucher;
import com.ou.demo.service.Vouchers.VoucherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/voucher")
    public ResponseEntity<?> createStore(@RequestBody Voucher vou) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            Voucher v = VoucherService.create(vou);
            return new ResponseEntity<>(v == null ? "error find voucher"
                    : new ResponseEntity(v, HttpStatus.OK), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("no accept", HttpStatus.UNAUTHORIZED);
        }

    }

    @GetMapping("/voucher/{id}")
    public ResponseEntity<?> getStore( @PathVariable("id") int id) {
            Voucher v = VoucherService.findByid(id);
            return new ResponseEntity<>(v == null ? "error find voucher"
                    : new ResponseEntity(v, HttpStatus.OK), HttpStatus.BAD_REQUEST);
       
    }

    @GetMapping("/voucher/code")
    public ResponseEntity<?> getByCode(@RequestParam String code) {
        
         
            Voucher v = VoucherService.findByCode(code);

            if (v == null) {
                return new ResponseEntity<>("error find voucher",
                        HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(v, HttpStatus.OK);
            }

       
    }

}
