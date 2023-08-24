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
@Table(name = "voucher_money")
@NamedQueries({
    @NamedQuery(name = "VoucherMoney.findAll", query = "SELECT v FROM VoucherMoney v"),
    @NamedQuery(name = "VoucherMoney.findByMoney", query = "SELECT v FROM VoucherMoney v WHERE v.money = :money"),
    @NamedQuery(name = "VoucherMoney.findByVoucherId", query = "SELECT v FROM VoucherMoney v WHERE v.voucherId = :voucherId")})
public class VoucherMoney implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "money")
    private double money;
    @Id
    @Basic(optional = false)
    @Column(name = "voucher_id")
    private Integer voucherId;
    @JoinColumn(name = "voucher_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Voucher voucher;

    public VoucherMoney() {
    }

    public VoucherMoney(Integer voucherId) {
        this.voucherId = voucherId;
    }

    public VoucherMoney(Integer voucherId, double money) {
        this.voucherId = voucherId;
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (voucherId != null ? voucherId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VoucherMoney)) {
            return false;
        }
        VoucherMoney other = (VoucherMoney) object;
        if ((this.voucherId == null && other.voucherId != null) || (this.voucherId != null && !this.voucherId.equals(other.voucherId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ou.demo.pojos.VoucherMoney[ voucherId=" + voucherId + " ]";
    }
    
}
