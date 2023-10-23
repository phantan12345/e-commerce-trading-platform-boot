/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.repositories;

import com.ou.demo.pojos.Orderdetail;
import com.ou.demo.pojos.ProductStore;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author ADMIN
 */
public interface OrderdetailRepository extends JpaRepository<Orderdetail, Integer> {

    Orderdetail findByproductStoreId(ProductStore ps);

    @Query("SELECT o FROM Orderdetail o WHERE MONTH(o.orderId.orderDate) = ?1 AND YEAR(o.orderId.orderDate) = ?2")
    List<Orderdetail> findByMonthAndYear(int month, int year);

}
