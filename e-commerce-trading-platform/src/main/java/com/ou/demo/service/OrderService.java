/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service;

import com.ou.demo.dto.DateDto;
import com.ou.demo.pojos.Order1;
import com.ou.demo.pojos.Store;

/**
 *
 * @author ADMIN
 */
public interface OrderService {

    Order1 create(Order1 o);

    Object findOrderByStore(Store store, DateDto dto);
}
