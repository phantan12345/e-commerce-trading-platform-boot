/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.impl;

import com.ou.demo.dto.CartDto;
import com.ou.demo.dto.CartInput;
import com.ou.demo.pojos.Order1;
import com.ou.demo.pojos.Orderdetail;
import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.ProductStore;
import com.ou.demo.pojos.User;
import com.ou.demo.pojos.Voucher;
import com.ou.demo.service.OrderService;
import com.ou.demo.service.OrderdetailService;
import com.ou.demo.service.ProductService;
import com.ou.demo.service.ProductStoreService;
import com.ou.demo.service.receiptService;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
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

    @Override
    public Object addReceipt(CartInput carts, User user) {
        try {

            Order1 order = new Order1();
            order.setOrderDate(new Date());
            Order1 o = OrderService.create(order);

            for (CartDto c : carts.getCarts()) {
                Orderdetail d = new Orderdetail();
                d.setQuatity(c.getCount());
                Product p = ProductService.findById(c.getId());
                if (p.getActive() == Boolean.TRUE) {
                    ProductStore ps = ProductStoreService.findByProduct(p);
                    if (c.getCount() < ps.getCount()) {
                        d.setProductStoreId(ps);
                        if (d.getProductStoreId().getVoucherId() != null) {
                            BigDecimal total = c.getPrice();

                            d.setTotal(total);
                            if (user.getVoucherSet().remove(d.getProductStoreId().getVoucherId())) {

                                return "voucher da xoa";
                            }

                        } else {
                            d.setTotal(p.getPrice().multiply(new BigDecimal(c.getCount())));

                        }
                        d.setOrderId(o);
                        Orderdetail od = OrderdetailService.create(d);
                        order.setPaymentId(carts.getPayment());
                        order.setActive(Boolean.TRUE);
                        int count = ps.getCount() - od.getQuatity();
                        ps.setCount(count);
                        ProductStoreService.create(ps);

                    } else {
                        return false;
                    }
                } else {
                    return p.getProductName() + " đã dừng bán";

                }

            }
        } catch (Exception ex) {
            return false;
        }
        return false;

    }

}
