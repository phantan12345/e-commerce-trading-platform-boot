/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.pojos.Category;
import com.ou.demo.service.CategoryService;
//import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class CategoryController {

    private CategoryService CategoryService;

    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(CategoryService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/category/")
    public ResponseEntity<?> create(@RequestBody Category c) {
        Category cate = CategoryService.create(c, 0);
        if (cate == null) {
            return new ResponseEntity<>("error create", HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(cate, HttpStatus.OK);
    }

    @PostMapping("/category/{id}/")
    public ResponseEntity<?> createCuren(@RequestBody Category c, @PathVariable("id") int id) {
        Category cate = CategoryService.create(c, id);

        return ResponseEntity.ok(cate);
    }

    @GetMapping("/category/{id}/")
    public ResponseEntity<?> getByID(@PathVariable("id") int id) {
        return ResponseEntity.ok(CategoryService.getCateByCateId(id));
    }

    @PutMapping("/category/{id}/")
    public ResponseEntity<?> updateCate(@RequestBody Category c, @PathVariable("id") int id) {
        return ResponseEntity.ok(CategoryService.update(c, id));
    }

}
