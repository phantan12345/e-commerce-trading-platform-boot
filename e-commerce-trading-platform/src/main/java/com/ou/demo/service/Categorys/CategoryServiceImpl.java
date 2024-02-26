/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Categorys;

import com.ou.demo.pojos.Category;
import com.ou.demo.repositories.CategoryReponsitory;
import com.ou.demo.service.Categorys.DTO.CategoryDTO;
import com.ou.demo.util.Service.Crud;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class CategoryServiceImpl extends Crud<Category,CategoryDTO> implements ICategoryService{

    @Autowired
    private CategoryReponsitory categoryReponsitory;

   
    @Override
    public Category findCateByName(String name) {
        return categoryReponsitory.findCateByName(name);
    }



   

    @Override
    public Category findCateById(int id) {
        return categoryReponsitory.findById(id).get();
    }





}
