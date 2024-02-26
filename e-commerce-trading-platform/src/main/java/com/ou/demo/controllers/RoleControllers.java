/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.pojos.Category;
import com.ou.demo.pojos.Role;
import com.ou.demo.service.Roles.DTO.RoleDto;
import com.ou.demo.service.Roles.IRoleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
public class RoleControllers {
    
    IRoleService roleService;
    
    @GetMapping("/roles")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(roleService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/role")
    public ResponseEntity<?> create(RoleDto dto) {
        Role role = roleService.create(dto);
        if (role == null) {
            return new ResponseEntity<>("error create", HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
