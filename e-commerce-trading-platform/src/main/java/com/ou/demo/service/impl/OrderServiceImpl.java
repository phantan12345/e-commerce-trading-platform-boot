/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.impl;

import com.ou.demo.pojos.Order1;
import com.ou.demo.repositories.OrderReponsitory;
import com.ou.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderReponsitory OrderReponsitory;
            
    @Override
    public Order1 create(Order1 o) {
        
        return OrderReponsitory.save(o);
    }
    
}
