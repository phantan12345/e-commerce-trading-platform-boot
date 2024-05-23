/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Orders;

import com.ou.demo.service.OrderDetails.DTO.DateDto;
import com.ou.demo.service.OrderDetails.DTO.StatDto;
import com.ou.demo.pojos.Order1;
import com.ou.demo.pojos.Orderdetail;
import com.ou.demo.pojos.ProductStore;
import com.ou.demo.pojos.Store;
import com.ou.demo.pojos.User;
import com.ou.demo.repositories.OrderReponsitory;
import com.ou.demo.service.ProductStores.ProductStoreService;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ou.demo.service.OrderDetails.IOrderdetailService;
import java.math.BigDecimal;

/**
 *
 * @author ADMIN
 */
@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderReponsitory OrderReponsitory;

    @Autowired
    private IOrderdetailService OrderdetailService;

    @Override
    public Order1 create(Order1 o) {

        return OrderReponsitory.save(o);
    }

    @Override
    public Object stat(Store store, DateDto monthAndYear) {

        List<Object[]> date = OrderdetailService.findByDate(monthAndYear.getMonth(), monthAndYear.getYear());
        return date.stream().map(dto -> new StatDto(
                Integer.valueOf(dto[0].toString()),
                new BigDecimal(dto[0].toString()))).toList();

    }

    @Override
    public Set<Order1> GetByUserId(User user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
