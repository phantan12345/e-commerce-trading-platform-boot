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
import java.time.LocalDate;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "order1")
@Data
public class Order1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "is_delete")
    private boolean isDelete;
    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private Float total;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private Set<Shipment> shipmentSet;
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    @ManyToOne
    private Payment paymentId;
    @JoinColumn(name = "UserID", referencedColumnName = "id")
    @ManyToOne
    private User userID;
    @JoinColumn(name = "voucher_id", referencedColumnName = "id")
    @ManyToOne
    private Voucher voucherId;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private Set<Orderdetail> orderdetailSet;

    public Order1() {
    }

    public Order1(Float total, User userID, Voucher voucherId) {
        this.isDelete = false;
        this.orderDate = new Date();
        this.total = total;
        this.userID = userID;
        this.voucherId = voucherId;
    }

    public Order1(int id) {
        this.id=id;
    }

}
