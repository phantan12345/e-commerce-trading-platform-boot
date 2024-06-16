/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Receipts;

import com.ou.demo.exceptions.ShipmentException;
import com.ou.demo.service.Receipts.DTO.CartDto;
import com.ou.demo.service.Receipts.DTO.CartInput;
import com.ou.demo.pojos.Order1;
import com.ou.demo.pojos.Orderdetail;
import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.Shipment;
import com.ou.demo.pojos.User;
import com.ou.demo.pojos.Voucher;
import com.ou.demo.repositories.OrderReponsitory;
import com.ou.demo.repositories.PaymentReponsitory;
import com.ou.demo.repositories.ProductReponsitory;
import com.ou.demo.repositories.ShipmentReponsitory;
import com.ou.demo.service.Vouchers.VoucherService;
import jakarta.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ou.demo.service.OrderDetails.IOrderdetailService;
import com.ou.demo.service.Products.IProductService;
import com.ou.demo.service.Orders.IOrderService;
import com.ou.demo.service.VNPlay.VNPayService;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
@Service
public class ReceiptService implements IReceiptService {

    @Autowired
    private IOrderService OrderService;

    @Autowired
    private IOrderdetailService OrderdetailService;

    @Autowired
    private ProductReponsitory ProductService;

    @Autowired
    private VoucherService VoucherService;

    @Autowired
    private ShipmentReponsitory shipmentReponsitory;

    @Autowired
    private PaymentReponsitory PaymentReponsitory;

    @Override
    public Order1 addReceipt(CartInput carts) {
        try {
            Order1 order = new Order1(carts.getTotal(), carts.getUser());

            System.out.println(order.getId());
            order.setPaymentId(PaymentReponsitory.findById(carts.getPayment()).get());
            Order1 o = OrderService.create(order);

            for (CartDto c : carts.getCarts()) {
                Orderdetail d = new Orderdetail();
                d.setQuatity(c.getCount());
                d.setTotal(c.getPrice());
                d.setDelete(false);
                Product p = ProductService.findById(c.getId()).get();
                d.setProductId(p);
                d.setOrderId(o);
                Orderdetail od = OrderdetailService.create(d);

                if (!updateCount(c.getId(), c.getCount())) {
                    throw new ShipmentException("Product quantity is too large");

                }
                Shipment shipment = new Shipment(carts.getAddress(), "Wait for confirmation", od);
                shipmentReponsitory.save(shipment);

            }
            return o;
        } catch (Exception ex) {
            return null;
        }

    }

    private boolean updateCount(int id, int count) {
        Product product = ProductService.findById(id).get();
        if (product.getCount() - count < 0) {
            return false;
        }
        product.setCount(product.getCount() - count);
        ProductService.save(product);

        return true;
    }

}
