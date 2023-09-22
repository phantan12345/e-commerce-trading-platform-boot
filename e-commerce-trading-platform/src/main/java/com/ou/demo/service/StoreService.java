/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service;

import com.ou.demo.pojos.Store;
import com.ou.demo.pojos.User;

/**
 *
 * @author ADMIN
 */
public interface StoreService {

    Store Create(Store store, User u);

    Store update(Store store);

    Store findStoreByUserID(User id);
    
    Store findStoreById(int id);

}
