/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.persistence.*;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "store")
@Data
public class Store {

    @Id
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "address")
    private String address;
    @Column(name = "is_delete")
    private Boolean isDelete;

    
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private User user;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "storeId")
    private Set<ProductStore> productStoreSet;

    public Store() {
    }

    public Store(String address, User u) {
        this.address = address;
        this.isDelete = false;
        this.userId = u.getId();
        
    }
    
    

}
