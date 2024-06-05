/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "shipment")
@Data
public class Shipment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "address")
    private String address;

    @Column(name = "active")
    private String active;

    @JsonIgnore
    @JoinColumn(name = "orderdetail_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Orderdetail orderdetailId;

    public Shipment() {
    }

    public Shipment(Integer id) {
        this.id = id;
    }

    public Shipment(String address, String active, Orderdetail Orderdetail) {

        this.address = address;
        this.active = active;
        this.orderdetailId = Orderdetail;
    }

}
