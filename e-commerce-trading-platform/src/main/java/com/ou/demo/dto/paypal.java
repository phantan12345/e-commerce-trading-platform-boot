/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author ADMIN
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class paypal {

    @NotNull(message = "PRICE IS NULL")
    private double price;
    @NotNull(message = "CURRENCY IS NULL")
    private String currency;
    @NotNull(message = "METHOD IS NULL")
    private String method;
    @NotNull(message = "INTENT IS NULL")
    private String intent;
    @NotNull(message = "DESCRIPTION IS NULL")
    private String description;
}
