/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.impl;

import com.ou.demo.pojos.Category;
import com.ou.demo.repositories.CategoryReponsitory;
import com.ou.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryReponsitory categoryReponsitory;
    
    @Override
    public Category findCateByName(String name) {
        return categoryReponsitory.findCateByName(name);
    }
    
}
