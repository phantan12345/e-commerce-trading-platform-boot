/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.dto;

import com.ou.demo.pojos.Category;
import com.ou.demo.pojos.ProductImage;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ADMIN
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdcutDto {

    private Integer id;
    @NotBlank(message = "PRODUCT NAME IS NULL")
    private String productName;
    @NotBlank(message = "PRICE  IS NULL")
    private BigDecimal price;
    private String voucher;
    @NotBlank(message = "IMAGE  IS NULL")
    private List<String> productImage;
    @NotBlank(message = "NOT CATEGORY")
    private Category categoryId;
    
}
