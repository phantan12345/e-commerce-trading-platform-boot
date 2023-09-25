/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.pojos.User;
import com.ou.demo.pojos.Voucher;
import com.ou.demo.security.JwtUtils;
import com.ou.demo.service.UserService;
import com.ou.demo.service.UserVoucherService;
import com.ou.demo.service.VoucherService;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@CrossOrigin

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UserVoucherController {

    private UserService UserService;

    private UserVoucherService UserVoucherService;

    private VoucherService VoucherService;

    @PostMapping("user/voucher/{id}")
    public ResponseEntity<?> getStore(@PathVariable("id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = UserService.findByUsername(userDetails.getUsername());

            Voucher v = VoucherService.findByid(id);

            Set<Voucher> Vou = user.getVoucherSet();
            Vou.add(v);
            user.setVoucherSet(Vou);

            return new ResponseEntity<>(UserVoucherService.addVoucherUser(user, v), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no accept", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("product/voucher/{id}")
    public ResponseEntity<?> getVouByProduct(@PathVariable("id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = UserService.findByUsername(userDetails.getUsername());

            Voucher v = VoucherService.findByid(id);

            Set<Voucher> Vou = user.getVoucherSet();
            Vou.add(v);
            user.setVoucherSet(Vou);

            return new ResponseEntity<>(UserVoucherService.addVoucherUser(user, v), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no accept", HttpStatus.UNAUTHORIZED);
        }
    }
}
