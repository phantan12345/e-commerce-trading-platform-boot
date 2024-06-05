/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Users.DTO;

import com.ou.demo.pojos.Role;
import jakarta.persistence.Column;
import java.util.List;
import java.util.Set;
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

    public Integer id;
    public String username;
    public String password;
    public String avatar;
    public String email;
    public String phone;
    public boolean isDelete;
    public String acceptToken;
    public Role roleId;
    public String name;

}
