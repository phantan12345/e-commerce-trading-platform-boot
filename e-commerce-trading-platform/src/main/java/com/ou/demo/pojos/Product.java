/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import jakarta.persistence.*;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "product_name")
    private String productName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "is_delete")
    private boolean isDelete;
    @Column(name = "count")
    private Integer count;
    @Column(name = "description")
    private String description;
    
    @JsonIgnore
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne
    private Category categoryId;
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Set<ProductImage> productImageSet;
    @OneToMany(mappedBy = "productId")
    private Set<Evaluate> evaluatesSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Set<Review> reviewSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Set<Orderdetail> orderdetailSet;

    public Product() {
    }

}
