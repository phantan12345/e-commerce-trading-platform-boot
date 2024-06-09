/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Products.DTO;

import com.ou.demo.pojos.Category;
import com.ou.demo.pojos.ProductImage;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ADMIN
 */
@Data
@Builder
public class ProductDto {

    public Integer id;
    public String productName;
    public BigDecimal price;
    public int count;
    public String voucher;
    private Set<ProductImage> productImageSet;
    public Category categoryId;
    public String description;

    public ProductDto() {
    }

    public ProductDto(Integer id, String productName, BigDecimal price, int count, String voucher, Set<ProductImage> productImageSet, Category categoryId,String description) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.count = count;
        this.voucher = voucher;
        this.productImageSet = productImageSet;
        this.categoryId = categoryId;
        this.description=description;
    }
    
    

 

}
