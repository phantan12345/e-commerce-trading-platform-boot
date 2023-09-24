/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.impl;

import com.ou.demo.dto.DateDto;
import com.ou.demo.dto.StatDto;
import com.ou.demo.pojos.Order1;
import com.ou.demo.pojos.Orderdetail;
import com.ou.demo.pojos.ProductStore;
import com.ou.demo.pojos.Store;
import com.ou.demo.repositories.OrderReponsitory;
import com.ou.demo.service.OrderService;
import com.ou.demo.service.OrderdetailService;
import com.ou.demo.service.ProductStoreService;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderReponsitory OrderReponsitory;

    @Autowired
    private ProductStoreService ProductStoreService;

    @Autowired
    private OrderdetailService OrderdetailService;

    @Override
    public Order1 create(Order1 o) {

        return OrderReponsitory.save(o);
    }

    @Override
    public Object findOrderByStore(Store store, DateDto monthAndYear) {
        List<StatDto> listDto = new ArrayList<>();

        List<Orderdetail> date = OrderdetailService.findByDate(monthAndYear.getMonth(), monthAndYear.getYear());
        if (date != null) {
            for (Orderdetail d : date) {
                StatDto dto = StatDto.builder()
                        .date(d.getOrderId().getOrderDate())
                        .total(d.getTotal())
                        .name(d.getProductStoreId().getProductId().getProductName()).build();
                listDto.add(dto);
            }

        }

        return listDto;
    }

}
