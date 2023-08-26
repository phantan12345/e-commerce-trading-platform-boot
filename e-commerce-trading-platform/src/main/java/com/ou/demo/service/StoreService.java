/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service;

import com.ou.demo.repositories.StoreReponsitory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ADMIN
 */
public class StoreService {
    
    @Autowired
    private StoreReponsitory storeReponsitory;
    
    
    public boolean Create(){
        
        return false;
    }
}
