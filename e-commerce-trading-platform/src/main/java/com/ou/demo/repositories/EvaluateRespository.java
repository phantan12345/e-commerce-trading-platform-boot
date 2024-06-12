/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ou.demo.repositories;

import com.ou.demo.pojos.Evaluate;
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
public interface EvaluateRespository extends JpaRepository<Evaluate, Integer> {

    @Query("SELECT o FROM Evaluate o WHERE o.ProductId.id = ?1")
    List<Evaluate> findByProductId(int id);
}
