/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.impl;

import com.ou.demo.pojos.Category;
import com.ou.demo.repositories.CategoryReponsitory;
import com.ou.demo.service.CategoryService;
import java.util.List;
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

    @Override
    public List<Category> getAll() {
        return categoryReponsitory.findAll();
    }

    @Override
    public Category create(Category c,int id) {
        
        if(id!=0){
            Category cate=categoryReponsitory.findById(id).get();
            c.setCategoryId(cate);
        }       
        return categoryReponsitory.save(c);
    }

    @Override
    public Category findCateById(int id) {
        return categoryReponsitory.findById(id).get();
    }

    @Override
    public List<Category> getCateByCateId(int c) {
        Category cate=categoryReponsitory.findById(c).get();
        return categoryReponsitory.findCateByCateId(cate);
    }

    @Override
    public Category update(Category cate,int id) {
        Category c=categoryReponsitory.findById(id).get();
        c.setName(cate.getName());
        c.setCategoryId(cate.getCategoryId());
        return categoryReponsitory.save(c);
    }
    
}
