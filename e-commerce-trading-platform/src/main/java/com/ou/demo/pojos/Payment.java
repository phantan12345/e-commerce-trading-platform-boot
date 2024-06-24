/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import jakarta.persistence.*;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Data;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "payment")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "payment")
    private String payment;
    @Column(name = "is_delete")
    private boolean isDelete;

    @JsonIgnore
    @OneToMany(mappedBy = "paymentId")
    private Set<Order1> order1Set;

    public Payment() {
    }

}
