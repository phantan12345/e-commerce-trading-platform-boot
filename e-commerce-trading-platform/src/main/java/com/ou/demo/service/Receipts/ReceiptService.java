/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Receipts;

import com.ou.demo.service.Receipts.DTO.CartDto;
import com.ou.demo.service.Receipts.DTO.CartInput;
import com.ou.demo.pojos.Order1;
import com.ou.demo.pojos.Orderdetail;
import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.ProductStore;
import com.ou.demo.pojos.Shipment;
import com.ou.demo.pojos.User;
import com.ou.demo.pojos.Voucher;
import com.ou.demo.repositories.OrderReponsitory;
import com.ou.demo.repositories.ShipmentReponsitory;
import com.ou.demo.service.ProductStores.ProductStoreService;
import com.ou.demo.service.Vouchers.VoucherService;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
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
    private ProductStoreService ProductStoreService;

    @Autowired
    private IOrderService OrderService;

    @Autowired
    private IOrderdetailService OrderdetailService;

    @Autowired
    private IProductService ProductService;

    @Autowired
    private VoucherService VoucherService;

    @Autowired
    private ShipmentReponsitory shipmentReponsitory;

    @Override
    public Order1 addReceipt(CartInput carts) {
        try {
            Voucher vou = VoucherService.findByid(carts.getVoucher());
            Order1 order = new Order1(carts.getTotal(), carts.getUser(), vou);
            Order1 o = OrderService.create(order);
            Shipment shipment = new Shipment(carts.getAddress(), "Wait for confirmation", o);
            shipmentReponsitory.save(shipment);
            for (CartDto c : carts.getCarts()) {
                Orderdetail d = new Orderdetail();
                d.setQuatity(c.getCount());
                Product p = ProductService.findById(c.getId());
                if (p.getDelte() == Boolean.FALSE) {
                    ProductStore ps = ProductStoreService.findByProduct(p);
                    if (ps == null) {
                        throw new NullPointerException("product-store is null");
                    }
                    if (c.getCount() < ps.getCount()) {
                        d.setProductStoreId(ps);
                        BigDecimal total = c.getPrice();

                        d.setTotal(total);

                        d.setOrderId(o);
                        Orderdetail od = OrderdetailService.create(d);

                        int count = ps.getCount() - od.getQuatity();
                        ps.setCount(count);

                        ProductStoreService.create(ps);

                    } else {
                        return null;
                    }
                    return o;
                }

            }
        } catch (Exception ex) {
            return null;
        }
        return null;

    }

}
