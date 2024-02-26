/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.pojos.Category;
import com.ou.demo.service.Categorys.DTO.CategoryDTO;
import jakarta.validation.Valid;
//import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ou.demo.service.Categorys.ICategoryService;

/**
 *
 * @author ADMIN
 */
@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class CategoryController {

    private ICategoryService CategoryService;

    @GetMapping("/categorys")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(CategoryService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<?> create(@RequestBody @Valid CategoryDTO c) {
        Category cate = CategoryService.Create(c);
        if (cate == null) {
            return new ResponseEntity<>("error create", HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(cate, HttpStatus.OK);
    }

//    @PostMapping("/category/{id}/")
//    public ResponseEntity<?> createCuren(@RequestBody Category c, @PathVariable("id") int id) {
//        Category cate = CategoryService.create(c, id);
//
//        return ResponseEntity.ok(cate);
//    }
    

    @PutMapping("/category/{id}")
    public ResponseEntity<?> updateCate(@RequestBody CategoryDTO c, @PathVariable("id") int id) {
        return ResponseEntity.ok(CategoryService.Update(c, id));
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<?> updateCate( @PathVariable("id") int id) {
        return ResponseEntity.ok(CategoryService.Delete(id));
    }

}
