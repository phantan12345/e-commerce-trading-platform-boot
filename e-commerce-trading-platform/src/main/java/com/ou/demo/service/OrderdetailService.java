/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service;

import com.ou.demo.pojos.Orderdetail;
import com.ou.demo.pojos.ProductStore;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADMIN
 */
@Repository
@Transactional
public interface OrderdetailService {

    Orderdetail create(Orderdetail od);

    Orderdetail findByProductStore(ProductStore ps);

    List<Orderdetail> findByDate(int month, int year);
}
