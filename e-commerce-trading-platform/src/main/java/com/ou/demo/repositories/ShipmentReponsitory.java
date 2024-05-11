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

    @Query(value = "SELECT s.* "
            + "FROM shipment s "
            + "JOIN orderdetail o ON s.order_id = o.order_id "
            + "JOIN product_store ps ON o.product_store_id = ps.id"
            + " WHERE ps.store_id = :userId", nativeQuery = true)
    List<Shipment> findShipmentByCurrntUser(@Param("userId") int id);
}
