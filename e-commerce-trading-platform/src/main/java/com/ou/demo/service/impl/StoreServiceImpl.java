/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.impl;

import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.ProductStore;
import com.ou.demo.pojos.Role;
import com.ou.demo.pojos.Store;
import com.ou.demo.pojos.User;
import com.ou.demo.repositories.StoreReponsitory;
import com.ou.demo.service.ProductService;
import com.ou.demo.service.RoleService;
import com.ou.demo.service.StoreService;
import com.ou.demo.service.UserService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreReponsitory storeReponsitory;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService ProductService;

    @Autowired
    private RoleService RoleService;

    @Override
    public Store Create(Store store, User u) {

        store.setUserId(u);
        store.setActive(Boolean.FALSE);
        Role role = RoleService.findRoleByRoleName("SELLER");
        if (role != null) {
            u.setRoleId(role);
            userService.update(u);
            return this.storeReponsitory.save(store);
        }

        return null;

    }

    @Override
    public Store findStoreByUserID(User id) {
        return storeReponsitory.findStoreByUserID(id);
    }

    @Override
    public Store update(Store id) {
        try {

            return this.storeReponsitory.save(id);

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Store findStoreById(int id) {
        return storeReponsitory.findById(id).get();
    }

    @Override
    public Store delete(Store s) {
        for (ProductStore ps : s.getProductStoreSet()) {
            Product p = ps.getProductId();
            p.setActive(Boolean.FALSE);
            ProductService.update(p);
        }
        s.setActive(Boolean.FALSE);
        return storeReponsitory.save(s);
    }
}
