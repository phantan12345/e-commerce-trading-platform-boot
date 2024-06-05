/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.repositories;

import com.ou.demo.pojos.Order1;
import com.ou.demo.pojos.Orderdetail;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ADMIN
 */
public interface OrderdetailRepository extends JpaRepository<Orderdetail, Integer> {


    @Query(value = "SELECT "
            + "day(o.order_date) as day, "
            + "sum(o.total) as total \n"
            + "FROM orderdetail od\n"
            + "JOIN order1 o ON od.order_id = o.id\n"
            + "WHERE month(o.order_date) = ?1 AND year(o.order_date) = ?2 \n"
            + "GROUP BY day(o.order_date)",nativeQuery = true)
    List<Object[]> findByMonthAndYear(@Param("month") int month, @Param("year") int year);

}
