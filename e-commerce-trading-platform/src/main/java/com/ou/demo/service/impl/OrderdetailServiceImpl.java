/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.impl;

import com.ou.demo.pojos.Orderdetail;
import com.ou.demo.repositories.OrderdetailRepository;
import com.ou.demo.service.OrderdetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class OrderdetailServiceImpl implements OrderdetailService{

    @Autowired
    private OrderdetailRepository OrderdetailRepository;
    
    @Override
    public Orderdetail create(Orderdetail od) {
        return OrderdetailRepository.save(od);
    }
    
}
