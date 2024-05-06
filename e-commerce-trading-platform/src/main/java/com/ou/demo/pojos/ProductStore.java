/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
@Table(name = "product_store")
@Data
public class ProductStore implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    
    @JsonIgnore
    @JoinColumn(name = "store_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Store storeId;
    @Column(name = "count")
    private Integer count;
    @Column(name = "is_delete")
    private boolean isDelete;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productStoreId")
    private Set<Wishlist> wishlistSet;
    
    
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Product productId;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productStoreId")
    private Set<Orderdetail> orderdetailSet;

    public ProductStore() {
    }


    
}
