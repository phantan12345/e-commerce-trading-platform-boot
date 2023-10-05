/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author ADMIN
 */
@Data
@AllArgsConstructor
@Builder
public class Login {

    @NotNull(message = "USERNAME IS NULL")
    private String username;

    @NotNull(message = "PASSWORD IS NULL")
    private String password;
}
