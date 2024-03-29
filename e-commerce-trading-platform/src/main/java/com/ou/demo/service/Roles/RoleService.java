/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Roles;

import com.ou.demo.pojos.Role;
import com.ou.demo.repositories.RoleReponsitory;
import com.ou.demo.service.Roles.DTO.RoleDto;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ou.demo.service.Roles.IRoleService;
import com.ou.demo.util.Service.Crud;

/**
 *
 * @author ADMIN
 */
@Service
public class RoleService extends Crud<Role, RoleDto> implements IRoleService {

    @Autowired
    private RoleReponsitory roleReponsitory;

    @Override
    public Optional<Role> getRoleById(int id) {
        return roleReponsitory.findById(id);
    }

    @Override
    public List<Role> getAll() {
        return (List<Role>) this.roleReponsitory.findAll();
    }

    @Override
    public Role findRoleByRoleName(String name) {

        return this.roleReponsitory.findRoleByRoleName(name);
    }

}
