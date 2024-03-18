/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.OrderDetails;

import com.ou.demo.pojos.Orderdetail;
import com.ou.demo.pojos.ProductStore;
import com.ou.demo.service.OrderDetails.DTO.OrderdetailDTO;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.ou.demo.util.Service.ICrud;

/**
 *
 * @author ADMIN
 */

public interface IOrderdetailService extends ICrud<Orderdetail, OrderdetailDTO>{

    Orderdetail create(Orderdetail od);

    Orderdetail findByProductStore(ProductStore ps);

    List<Object[]> findByDate(int month, int year);
}
