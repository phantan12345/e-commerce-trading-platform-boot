/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import jakarta.persistence.*;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "email")
    private String email;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "is_delete")
    private boolean isDelete;
    @Column(name = "accept_token")
    private String acceptToken;

  
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Store store;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Set<Review> reviewSet;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sentBy")
    private Set<Messages> messagesSet;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sentTo")
    private Set<Messages> messagesSet1;

    @JsonIgnore
    @OneToMany(mappedBy = "userID")
    private Set<Order1> order1Set;

  

    @JsonIgnore
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ManyToOne
    private Role roleId;

    public User() {
    }

    public User(String name, String username, String password, String avatar, String email,  String phone, Role roleId) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.email = email;
        this.active = true;
        this.phone = phone;
        this.isDelete = false;
        this.roleId = roleId;
    }

    public User(int id) {
        this.id = id;
    }

}
