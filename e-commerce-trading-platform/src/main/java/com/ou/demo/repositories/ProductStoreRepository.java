/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.repositories;

import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.ProductStore;
import com.ou.demo.pojos.Store;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADMIN
 */
@Repository
@Transactional
public interface ProductStoreRepository extends JpaRepository<ProductStore, Integer> {

    @Query("SELECT p FROM ProductStore p WHERE p.productId = ?1")
    ProductStore findByProduct(Product id);

    @Query("SELECT p FROM ProductStore p WHERE p.storeId = ?1")
    List<ProductStore> findByStore(Store id);

//    @Query("select ps.id,od.total,o.order_date,s.store_name\n"
//            + "from Order1 o , orderdetail od,ProductStore ps , Store s\n"
//            + "where o.id=od.order_id and ps.id=od.product_store_id and s.id=ps.store_id\n"
//            + "and o.active=1 and month(o.order_date)=?1 and year(o.order_date)=?2 and s.storeName=?3"
//            + "order by o.order_date asc")
//    List<ProductStore> stat(int month ,int year,String name);
}
