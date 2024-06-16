/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.repositories;

import com.ou.demo.pojos.Shipment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ADMIN
 */
@Repository
public interface ShipmentReponsitory extends JpaRepository<Shipment, Integer> {

    @Query(value = "SELECT s.*,p.product_name,p.id, od.quatity,img.url, od.total "
            + "     FROM shipment s"
            + "     JOIN orderdetail od ON s.orderdetail_id = od.id "
            + "     JOIN order1 o ON o.id = od.order_id "
            + "     JOIN product p ON p.id = od.product_id "
            + "     JOIN product_image img ON img.product_id = p.id "
            + "      WHERE o.UserID=?1", nativeQuery = true)
    List<Object[]> findShipmentByCurrntUser(@Param("userId") int id);

        @Query(value = "SELECT s.*,p.product_name, od.quatity,img.url , od.total "
            + "     FROM shipment s"
            + "     JOIN orderdetail od ON s.orderdetail_id = od.id "
            + "     JOIN order1 o ON o.id = od.order_id "
            + "     JOIN product p ON p.id = od.product_id "
            + "     JOIN product_image img ON img.product_id = p.id ", nativeQuery = true)
    List<Object[]> findShipmentByAdmin();
}
