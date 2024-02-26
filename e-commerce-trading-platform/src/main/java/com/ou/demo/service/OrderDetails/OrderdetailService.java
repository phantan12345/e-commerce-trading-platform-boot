/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.OrderDetails;

import com.ou.demo.pojos.Orderdetail;
import com.ou.demo.pojos.ProductStore;
import com.ou.demo.repositories.OrderdetailRepository;
import com.ou.demo.repositories.WishlistRepository;
import com.ou.demo.service.OrderDetails.DTO.OrderdetailDTO;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ou.demo.service.OrderDetails.IOrderdetailService;

/**
 *
 * @author ADMIN
 */
@Service
public class OrderdetailService extends com.ou.demo.util.Service.Crud<Orderdetail, OrderdetailDTO> implements IOrderdetailService {

    @Autowired
    private OrderdetailRepository OrderdetailRepository;


    

    @Override
    public Orderdetail create(Orderdetail od) {
        return OrderdetailRepository.save(od);
    }

    @Override
    public Orderdetail findByProductStore(ProductStore ps) {
        return OrderdetailRepository.findByproductStoreId(ps);
    }

    @Override
    public List<Orderdetail> findByDate(int month, int year) {
        return OrderdetailRepository.findByMonthAndYear(month, year);
    }

}
