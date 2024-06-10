/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.OrderDetails;

import com.ou.demo.pojos.Orderdetail;
import com.ou.demo.service.OrderDetails.DTO.OrderdetailDTO;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.ou.demo.util.Service.ICrud;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author ADMIN
 */
public interface IOrderdetailService extends ICrud<Orderdetail, OrderdetailDTO> {

    Orderdetail create(Orderdetail od);

    @Query(value = "SELECT \n"
            + "    DAY(order_date) AS order_day,"
            + "    SUM(total) AS total_sum\n"
            + "FROM \n"
            + "    trading.order1\n"
            + "WHERE \n"
            + "    MONTH(order_date) = ?1 "
            + "    AND YEAR(order_date) = ?2 "
            + "GROUP BY \n"
            + "    DAY(order_date), \n"
            + "    YEAR(order_date)\n"
            + "ORDER BY \n"
            + "    order_day;", nativeQuery = true)
    List<Object[]> findByDate(int month, int year);
}
