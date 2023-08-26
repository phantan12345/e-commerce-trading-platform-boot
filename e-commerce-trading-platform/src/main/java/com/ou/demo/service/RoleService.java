/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service;

import com.ou.demo.pojos.Role;
import com.ou.demo.repositories.RoleReponsitory;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class RoleService {
    
    @Autowired
    private RoleReponsitory roleReponsitory;
    
    
    
    public Optional<Role> getRoleById(int id){
        return roleReponsitory.findById(id);
    }
}