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
@Table(name = "product_image")
@Data
public class ProductImage  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "url")
    private String url;
    @Column(name = "is_delete")
    private boolean isDelete;
    
    @JsonIgnore
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Product productId;

    public ProductImage() {
    }

}
