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
    @NotBlank
    private String productName;
    @NotBlank
    private BigDecimal price;
    @NotBlank
    private List<ProductImage> productImageSet;
    @NotBlank
    private Category categoryId;
}
