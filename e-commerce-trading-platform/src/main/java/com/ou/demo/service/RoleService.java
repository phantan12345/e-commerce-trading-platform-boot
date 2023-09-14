/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service;

import com.ou.demo.pojos.Role;
import com.ou.demo.repositories.RoleReponsitory;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public interface RoleService {
    
  
    
    
    
    public Optional<Role> getRoleById(int id);
    
    
    public Role create(Role  r);
    
    public List<Role> getAll();
    
    
    public Role findRoleByRoleName(String name);
    
}
