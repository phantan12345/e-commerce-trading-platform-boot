/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Users.DTO;

import com.ou.demo.pojos.Role;
import jakarta.persistence.Column;
import lombok.*;

/**
 *
 * @author ADMIN
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {

    private Integer id;
    private String username;
    private String password;
    private String avatar;
    private String email;
    private Boolean active;
    private String phone;
    private boolean isDelete;
    private String acceptToken;
    private String roleId;

}
