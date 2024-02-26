/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.pojos;

import java.io.Serializable;
import java.util.Set;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "product_store")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductStore.findAll", query = "SELECT p FROM ProductStore p"),
    @NamedQuery(name = "ProductStore.findById", query = "SELECT p FROM ProductStore p WHERE p.id = :id"),
    @NamedQuery(name = "ProductStore.findByCount", query = "SELECT p FROM ProductStore p WHERE p.count = :count"),
    @NamedQuery(name = "ProductStore.findByIsDelete", query = "SELECT p FROM ProductStore p WHERE p.isDelete = :isDelete")})
public class ProductStore implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "count")
    private Integer count;
    @Column(name = "is_delete")
    private Short isDelete;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productStoreId")
    private Set<Wishlist> wishlistSet;
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Product productId;
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Store storeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productStoreId")
    private Set<Orderdetail> orderdetailSet;

    public ProductStore() {
    }

    public ProductStore(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Short getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Short isDelete) {
        this.isDelete = isDelete;
    }

    @XmlTransient
    public Set<Wishlist> getWishlistSet() {
        return wishlistSet;
    }

    public void setWishlistSet(Set<Wishlist> wishlistSet) {
        this.wishlistSet = wishlistSet;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Store getStoreId() {
        return storeId;
    }

    public void setStoreId(Store storeId) {
        this.storeId = storeId;
    }

    @XmlTransient
    public Set<Orderdetail> getOrderdetailSet() {
        return orderdetailSet;
    }

    public void setOrderdetailSet(Set<Orderdetail> orderdetailSet) {
        this.orderdetailSet = orderdetailSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductStore)) {
            return false;
        }
        ProductStore other = (ProductStore) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ou.demo.pojos.ProductStore[ id=" + id + " ]";
    }
    
}
