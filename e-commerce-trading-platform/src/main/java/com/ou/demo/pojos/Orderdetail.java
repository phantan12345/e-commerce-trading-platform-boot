/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "orderdetail")
@Data
public class Orderdetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "quatity")
    private Integer quatity;
    @Column(name = "total")
    private BigDecimal total;
    @Column(name = "is_delete")
    private boolean isDelete;
    
    @JsonIgnore
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Order1 orderId;
    
    @JsonIgnore
    @JoinColumn(name = "product_store_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProductStore productStoreId;

    public Orderdetail() {
    }


    
}
