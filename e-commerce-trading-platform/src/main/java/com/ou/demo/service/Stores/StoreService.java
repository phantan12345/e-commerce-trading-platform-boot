/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Stores;

import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.ProductStore;
import com.ou.demo.pojos.Role;
import com.ou.demo.pojos.Store;
import com.ou.demo.pojos.User;
import com.ou.demo.repositories.StoreReponsitory;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ou.demo.service.Roles.IRoleService;
import com.ou.demo.service.Products.IProductService;
import com.ou.demo.service.Stores.DTO.StoreDTO;
import com.ou.demo.util.Service.Crud;
import com.ou.demo.service.Users.IUserService;
import com.ou.demo.service.Stores.IStoreService;

/**
 *
 * @author ADMIN
 */
@Service
public class StoreService extends Crud<Store, StoreDTO> implements IStoreService {

    @Autowired
    private StoreReponsitory storeReponsitory;

    @Autowired
    private IUserService userService;

    @Autowired
    private IProductService ProductService;

    @Autowired
    private IRoleService RoleService;

    @Override
    public Store Create(StoreDTO input, User u) {
        Store store=new Store( input.getAddress(),  u);
        Role role = RoleService.findRoleByRoleName("SALER");
        if (role != null) {
            u.setRoleId(role);
            u.setName(input.getName());
            userService.update(u);
            return this.storeReponsitory.save(store);
        }

        return null;

    }

    



    @Override
    public Store findStoreById(int id) {
        return storeReponsitory.findById(id).get();
    }



 

    @Override
    public List<Store> getStores() {
        return  storeReponsitory.findAll();
    }
}