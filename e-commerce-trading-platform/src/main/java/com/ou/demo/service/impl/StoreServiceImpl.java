/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.impl;

import com.ou.demo.pojos.Store;
import com.ou.demo.pojos.User;
import com.ou.demo.repositories.StoreReponsitory;
import com.ou.demo.service.StoreService;
import com.ou.demo.service.UserService;
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

    @Override
    public Store Create(Store store, User u) {

        try {

            store.setUserId(u);
            store.setActive(Boolean.FALSE);
            userService.updateActice(u.getId());
            return this.storeReponsitory.save(store);

        } catch (Exception e) {
            return null;
        }
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
}
