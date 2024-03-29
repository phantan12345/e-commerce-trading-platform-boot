/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.ProductStores.DTO;

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

/**
 *
 * @author ADMIN
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductStoreDto {
    private String productName;
    private BigDecimal price;
    private List<String> productImage;
    private Category categoryId;
    private Store store;
}
