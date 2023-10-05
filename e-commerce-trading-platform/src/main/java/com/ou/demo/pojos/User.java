/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByAvatar", query = "SELECT u FROM User u WHERE u.avatar = :avatar"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByActive", query = "SELECT u FROM User u WHERE u.active = :active"),
    @NamedQuery(name = "User.findByPhone", query = "SELECT u FROM User u WHERE u.phone = :phone")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @NotNull(message = "NOT NULL")
    @NotBlank(message = "NOT NULL")
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    @NotNull(message = "NOT NULL")
    @NotBlank(message = "NOT NULL")
    private String password;
    @NotNull(message = "NOT NULL")
    @NotBlank(message = "NOT NULL")
    @Column(name = "avatar")
    private String avatar;
    @NotNull(message = "NOT NULL")
    @NotBlank(message = "NOT NULL")
    @Column(name = "email")
    @Email(message = "NOT FORMAT")
    private String email;
    @Column(name = "active")
    private Boolean active;
    @NotNull(message = "NOT NULL")
    @NotBlank(message = "NOT NULL")
    @Column(name = "Phone")
    private String phone;
    @JoinTable(name = "user_voucher", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "voucher_id", referencedColumnName = "id")})
    @ManyToMany
    private Set<Voucher> voucherSet;
    @JsonIgnore

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Set<Store> storeSet;
    @JsonIgnore

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Set<Reriew> reriewSet;
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Role roleId;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Voucher> getVoucherSet() {
        return voucherSet;
    }

    public void setVoucherSet(Set<Voucher> voucherSet) {
        this.voucherSet = voucherSet;
    }

    public Set<Store> getStoreSet() {
        return storeSet;
    }

    public void setStoreSet(Set<Store> storeSet) {
        this.storeSet = storeSet;
    }

    public Set<Reriew> getReriewSet() {
        return reriewSet;
    }

    public void setReriewSet(Set<Reriew> reriewSet) {
        this.reriewSet = reriewSet;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ou.demo.pojos.User[ id=" + id + " ]";
    }

}
