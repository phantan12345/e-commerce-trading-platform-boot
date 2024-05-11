/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Products.DTO;

import com.ou.demo.pojos.Category;
import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.ProductImage;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ADMIN
 */
@NoArgsConstructor
public class ProductSumary {

    public Product product;

    public int count;

    public ProductSumary(Product product, int count) {
        this.product = product;
        this.count = count;
    }

}
