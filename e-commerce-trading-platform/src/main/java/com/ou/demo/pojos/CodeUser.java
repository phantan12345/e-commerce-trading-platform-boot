/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.pojos;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "code_user")
@NamedQueries({
    @NamedQuery(name = "CodeUser.findAll", query = "SELECT c FROM CodeUser c"),
    @NamedQuery(name = "CodeUser.findById", query = "SELECT c FROM CodeUser c WHERE c.id = :id")})
public class CodeUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
    @JoinColumn(name = "voucher_code_voucher_id", referencedColumnName = "voucher_id")
    @ManyToOne(optional = false)
    private VoucherCode voucherCodeVoucherId;

    public CodeUser() {
    }

    public CodeUser(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public VoucherCode getVoucherCodeVoucherId() {
        return voucherCodeVoucherId;
    }

    public void setVoucherCodeVoucherId(VoucherCode voucherCodeVoucherId) {
        this.voucherCodeVoucherId = voucherCodeVoucherId;
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
        if (!(object instanceof CodeUser)) {
            return false;
        }
        CodeUser other = (CodeUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ou.demo.pojos.CodeUser[ id=" + id + " ]";
    }
    
}
