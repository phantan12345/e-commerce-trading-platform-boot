/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.pojos;

import java.io.Serializable;
import java.util.Set;
import jakarta.persistence.*;


/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "voucher_code")
@NamedQueries({
    @NamedQuery(name = "VoucherCode.findAll", query = "SELECT v FROM VoucherCode v"),
    @NamedQuery(name = "VoucherCode.findByCode", query = "SELECT v FROM VoucherCode v WHERE v.code = :code"),
    @NamedQuery(name = "VoucherCode.findByVoucherId", query = "SELECT v FROM VoucherCode v WHERE v.voucherId = :voucherId")})
public class VoucherCode implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "code")
    private int code;
    @Id
    @Basic(optional = false)
    @Column(name = "voucher_id")
    private Integer voucherId;
    @JoinColumn(name = "voucher_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Voucher voucher;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voucherCodeVoucherId")
    private Set<CodeUser> codeUserSet;

    public VoucherCode() {
    }

    public VoucherCode(Integer voucherId) {
        this.voucherId = voucherId;
    }

    public VoucherCode(Integer voucherId, int code) {
        this.voucherId = voucherId;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public Set<CodeUser> getCodeUserSet() {
        return codeUserSet;
    }

    public void setCodeUserSet(Set<CodeUser> codeUserSet) {
        this.codeUserSet = codeUserSet;
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
        if (!(object instanceof VoucherCode)) {
            return false;
        }
        VoucherCode other = (VoucherCode) object;
        if ((this.voucherId == null && other.voucherId != null) || (this.voucherId != null && !this.voucherId.equals(other.voucherId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ou.demo.pojos.VoucherCode[ voucherId=" + voucherId + " ]";
    }
    
}
