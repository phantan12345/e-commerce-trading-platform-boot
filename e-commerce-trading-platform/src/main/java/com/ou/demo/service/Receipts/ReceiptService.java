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
import com.ou.demo.pojos.User;
import com.ou.demo.pojos.Voucher;
import com.ou.demo.pojos.Wishlist;
import com.ou.demo.repositories.WishlistRepository;
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
    private WishlistRepository wishlistRepository;

    @Override
    public Object addReceipt(CartInput carts, User user) {
        try {
            Voucher vou = VoucherService.findByid(carts.getVoucher());
            Order1 order = new Order1();
            order.setOrderDate(new Date());
//            order.setTotal(carts.getTotal());
            order.setVoucherId(vou);
            Order1 o = OrderService.create(order);

            for (CartDto c : carts.getCarts()) {
                Orderdetail d = new Orderdetail();
                d.setQuatity(c.getCount());
                Product p = ProductService.findById(c.getId());
                if (p.getIsDelete() == Boolean.FALSE) {
                    ProductStore ps = ProductStoreService.findByProduct(p);
                    if (ps == null) {
                        throw new NullPointerException("product-store is null");
                    }
                    Wishlist wishlist = new Wishlist(ps, user);
                    wishlistRepository.save(wishlist);
                    if (c.getCount() < ps.getCount()) {
                        d.setProductStoreId(ps);
                        BigDecimal total = c.getPrice();

                        d.setTotal(total);

                        d.setOrderId(o);
                        Orderdetail od = OrderdetailService.create(d);

                        int count = ps.getCount() - od.getQuatity();
                        ps.setCount(count);
                        ProductStoreService.create(ps);
                        OrderService.create(o);

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
