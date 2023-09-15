/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.impl;

import com.ou.demo.dto.CartDto;
import com.ou.demo.pojos.Order1;
import com.ou.demo.pojos.Orderdetail;
import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.ProductStore;
import com.ou.demo.pojos.User;
import com.ou.demo.service.OrderService;
import com.ou.demo.service.OrderdetailService;
import com.ou.demo.service.ProductService;
import com.ou.demo.service.ProductStoreService;
import com.ou.demo.service.receiptService;
import jakarta.servlet.http.HttpSession;
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
    
    @Autowired
    private ProductService ProductService;
    
    
    @Autowired
    private HttpSession s;
    
  

    @Override
    public boolean addReceipt(Map<Integer, CartDto> carts, User user) {
        try {
            double total = 0;
            for (CartDto c : carts.values()) {
                total += (c.getPrice().multiply(new BigDecimal(c.getCount()))).doubleValue();
            }
            Order1 order = new Order1();
            order.setTotal(total);
            order.setOrderDate(new Date());
            Order1 o= OrderService.create(order);
            

            for (CartDto c : carts.values()) {
                Orderdetail d = new Orderdetail();
                d.setQuatity(c.getCount());
                Product p=ProductService.findById(c.getId());
                d.setProductStoreId(ProductStoreService.findByProduct(p));
                d.setTotal(c.getPrice().multiply(new BigDecimal(c.getCount())));
                d.setOrderId(o);
                OrderdetailService.create(d);
            }
            s.setAttribute("cart", null);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
