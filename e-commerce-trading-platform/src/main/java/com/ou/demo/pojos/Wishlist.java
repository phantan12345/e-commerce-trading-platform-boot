/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.pojos;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ADMIN
 */
@Data
@Entity
@Table(name = "wishlist")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wishlist.findAll", query = "SELECT w FROM Wishlist w"),
    @NamedQuery(name = "Wishlist.findById", query = "SELECT w FROM Wishlist w WHERE w.id = :id"),
    @NamedQuery(name = "Wishlist.findByIsDelete", query = "SELECT w FROM Wishlist w WHERE w.isDelete = :isDelete")})
public class Wishlist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "is_delete")
    private boolean isDelete;
    @JoinColumn(name = "product_store_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProductStore productStoreId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public Wishlist( ProductStore productStoreId, User userId) {
        this.isDelete = Boolean.FALSE;
        this.productStoreId = productStoreId;
        this.userId = userId;
    }

}
