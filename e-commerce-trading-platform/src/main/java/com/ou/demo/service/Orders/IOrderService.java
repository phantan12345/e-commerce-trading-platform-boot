/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Orders;

import com.ou.demo.service.OrderDetails.DTO.DateDto;
import com.ou.demo.pojos.Order1;
import com.ou.demo.pojos.User;
import java.util.Set;

/**
 *
 * @author ADMIN
 */
public interface IOrderService {

    Order1 create(Order1 o);

    Object stat( int month,int year);
    
    Set<Order1> GetByUserId(User user);
}
