/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import jakarta.persistence.*;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "voucher")
@NamedQueries({
    @NamedQuery(name = "Voucher.findAll", query = "SELECT v FROM Voucher v"),
    @NamedQuery(name = "Voucher.findById", query = "SELECT v FROM Voucher v WHERE v.id = :id"),
    @NamedQuery(name = "Voucher.findByDiscount", query = "SELECT v FROM Voucher v WHERE v.discount = :discount"),
    @NamedQuery(name = "Voucher.findByCode", query = "SELECT v FROM Voucher v WHERE v.code = :code")})
public class Voucher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "discount")
    private Double discount;
    @Column(name = "code")
    private String code;
    @JsonIgnore
    @OneToMany(mappedBy = "voucherId")
    private Set<ProductStore> productStoreSet;
    @JsonIgnore

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voucherId")
    private Set<CodeUser> codeUserSet;

    public Voucher() {
    }

    public Voucher(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<ProductStore> getProductStoreSet() {
        return productStoreSet;
    }

    public void setProductStoreSet(Set<ProductStore> productStoreSet) {
        this.productStoreSet = productStoreSet;
    }

    public Set<CodeUser> getCodeUserSet() {
        return codeUserSet;
    }

    public void setCodeUserSet(Set<CodeUser> codeUserSet) {
        this.codeUserSet = codeUserSet;
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
        if (!(object instanceof Voucher)) {
            return false;
        }
        Voucher other = (Voucher) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ou.demo.pojos.Voucher[ id=" + id + " ]";
    }

}
