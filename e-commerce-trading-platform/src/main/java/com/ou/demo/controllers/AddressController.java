/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.pojos.User;
import com.ou.demo.service.Address.DTO.AddressDto;
import com.ou.demo.service.Address.IAddressService;
import com.ou.demo.service.Receipts.DTO.CartInput;
import com.ou.demo.service.Users.DTO.CurrentUser;
import com.ou.demo.service.Users.DTO.UsersDto;
import com.ou.demo.service.Users.IUserService;
import java.io.UnsupportedEncodingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @Autowired
    private IUserService UserService;

    @GetMapping("/addresses")
    public ResponseEntity<?> getAllAddressCurentuser(@CurrentUser UsersDto currentUser) {

        User userCuren = UserService.findById(currentUser.getId());

        return new ResponseEntity<>(
                addressService.getAddresses(userCuren), HttpStatus.OK);
    }

    @PostMapping("/addresses")
    public ResponseEntity<?> addlAddressCurentuser(@CurrentUser UsersDto currentUser,AddressDto input) {

        User userCuren = UserService.findById(currentUser.getId());

        return new ResponseEntity<>(
                addressService.addAddress(userCuren,input), HttpStatus.OK);
    }
}
