/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Products.DTO;

import com.ou.demo.pojos.Category;
import com.ou.demo.pojos.ProductImage;
import com.ou.demo.pojos.Store;
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
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    public Integer id;
    @NotBlank(message = "PRODUCT NAME IS NULL")
    public String productName;
    @NotBlank(message = "PRICE  IS NULL")
    public BigDecimal price;
    public int count;
    public String voucher;
    @NotBlank(message = "IMAGE  IS NULL")
    private Set<ProductImage> productImageSet;
    @NotBlank(message = "NOT CATEGORY")
    public Category categoryId;
    public List<MultipartFile> file;

}
