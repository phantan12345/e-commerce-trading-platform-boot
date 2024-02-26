/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Categorys;

import com.ou.demo.pojos.Category;
import com.ou.demo.service.Categorys.DTO.CategoryDTO;
import java.util.List;
import com.ou.demo.util.Service.ICrud;

/**
 *
 * @author ADMIN
 */
public interface ICategoryService extends ICrud<Category,CategoryDTO>{


    Category findCateByName(String name);



    Category findCateById(int id);


}
