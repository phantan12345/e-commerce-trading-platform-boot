/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.impl;

import com.ou.demo.dto.CartDto;
import com.ou.demo.pojos.Order1;
import com.ou.demo.pojos.Orderdetail;
import com.ou.demo.pojos.User;
import com.ou.demo.service.OrderService;
import com.ou.demo.service.OrderdetailService;
import com.ou.demo.service.ProductStoreService;
import com.ou.demo.service.receiptService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class ReceiptServiceImpl implements receiptService {

    @Autowired
    private ProductStoreService ProductStoreService;

    @Autowired
    private OrderService OrderService;

    @Autowired
    private OrderdetailService OrderdetailService;

    @Override
    public boolean addReceipt(Map<String, CartDto> carts, User user) {
        try {
            double total = 0;
            for (CartDto c : carts.values()) {
                total += (c.getPrice().multiply(new BigDecimal(c.getCount()))).doubleValue();
            }
            Order1 order = new Order1();
            order.setTotal(total);
            order.setOrderDate(new Date());
            OrderService.create(order);

            for (CartDto c : carts.values()) {
                Orderdetail d = new Orderdetail();
                d.setQuatity(c.getCount());
                d.setProductStoreId(ProductStoreService.findByProduct(c.getId()));
                d.setTotal(c.getPrice().multiply(new BigDecimal(c.getCount())));
                OrderdetailService.create(d);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
