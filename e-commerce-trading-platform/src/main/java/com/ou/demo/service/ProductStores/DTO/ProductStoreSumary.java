/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.ProductStores.DTO;

import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.Store;
import com.ou.demo.service.Products.DTO.ProductDto;
import com.ou.demo.service.Products.DTO.ProductSumary;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author ADMIN
 */
@Data
@Builder
public class ProductStoreSumary {

    private Store store;
    private ProductDto product;

}
