/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.pojos.Evaluate;
import com.ou.demo.pojos.User;
import com.ou.demo.service.Evaluate.DTO.EvaluateDto;
import com.ou.demo.service.Evaluate.IEvaluateService;
import com.ou.demo.service.Users.DTO.CurrentUser;
import com.ou.demo.service.Users.DTO.UsersDto;
import com.ou.demo.service.Users.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@RestController
@RequestMapping("/api")
public class EvaluateController {

    @Autowired
    private IEvaluateService evaluateService;

    @Autowired
    private IUserService UserService;

    @GetMapping("/evaluate/{id}")
    public ResponseEntity<?> getAllAddressCurentuser(@PathVariable("id") int id) {

        return new ResponseEntity<>(
                evaluateService.findByProductId(id), HttpStatus.OK);
    }

    @PostMapping("/evaluate")
    public ResponseEntity<?> getAllAddressCurentuser(@CurrentUser UsersDto currentUser, @RequestBody EvaluateDto input) {
        User curentUser = UserService.findById(currentUser.getId());
        input.setUserId(curentUser);
        return new ResponseEntity<>(
                evaluateService.Create(input), HttpStatus.OK);
    }

}
