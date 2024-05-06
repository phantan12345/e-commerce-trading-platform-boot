/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import jakarta.persistence.*;

import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "wishlist")
@Data
public class Wishlist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "is_delete")
    private Short isDelete;
    
    @JsonIgnore
    @JoinColumn(name = "product_store_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProductStore productStoreId;
    
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public Wishlist() {
    }
    
    public Wishlist(ProductStore ps,User u){
            this.productStoreId=ps;
            this.userId=u;
    }

    
}
