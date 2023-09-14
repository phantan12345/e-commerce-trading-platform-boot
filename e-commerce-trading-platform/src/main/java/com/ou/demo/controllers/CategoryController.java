/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.pojos.Category;
import com.ou.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService CategoryService;

    @GetMapping("/category/")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(CategoryService.getAll());
    }

    @PostMapping("/category/")
    public ResponseEntity<?> create(@RequestBody Category c) {

        return ResponseEntity.ok(CategoryService.create(c, 0));
    }

    @PostMapping("/category/{id}/")
    public ResponseEntity<?> createCuren(@RequestBody Category c, @PathVariable("id") int id) {
        return ResponseEntity.ok(CategoryService.create(c, id));
    }

    @GetMapping("/category/{id}/")
    public ResponseEntity<?> getByID(@RequestBody Category c, @PathVariable("id") int id) {
        return ResponseEntity.ok(CategoryService.getCateByCateId(id));
    }

    @PutMapping("/category/{id}/")
    public ResponseEntity<?> updateCate(@RequestBody Category c, @PathVariable("id") int id) {
        return ResponseEntity.ok(CategoryService.update(c,id));
    }
}
