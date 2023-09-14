/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service;

import com.ou.demo.pojos.Category;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface CategoryService {

    Category create(Category c, int id);

    Category findCateByName(String name);

    List<Category> getAll();

    List<Category> getCateByCateId(int cate);

    Category findCateById(int id);
    
    Category update(Category cate,int id);
}
