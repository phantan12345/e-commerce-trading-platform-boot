/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import jakarta.persistence.*;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "reriew")
@NamedQueries({
    @NamedQuery(name = "Reriew.findAll", query = "SELECT r FROM Reriew r"),
    @NamedQuery(name = "Reriew.findById", query = "SELECT r FROM Reriew r WHERE r.id = :id"),
    @NamedQuery(name = "Reriew.findByComent", query = "SELECT r FROM Reriew r WHERE r.coment = :coment"),
    @NamedQuery(name = "Reriew.findByDateContent", query = "SELECT r FROM Reriew r WHERE r.dateContent = :dateContent"),
    @NamedQuery(name = "Reriew.findByEvaluate", query = "SELECT r FROM Reriew r WHERE r.evaluate = :evaluate")})
public class Reriew implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "coment")
    private String coment;
    @Column(name = "date_content")
    @Temporal(TemporalType.DATE)
    private Date dateContent;
    @Column(name = "evaluate")
    private Integer evaluate;
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Product productId;
    @JsonIgnore

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reriewId")
    private Set<Reriew> reriewSet;
    @JoinColumn(name = "reriew_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Reriew reriewId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public Reriew() {
    }

    public Reriew(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public Date getDateContent() {
        return dateContent;
    }

    public void setDateContent(Date dateContent) {
        this.dateContent = dateContent;
    }

    public Integer getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Integer evaluate) {
        this.evaluate = evaluate;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Set<Reriew> getReriewSet() {
        return reriewSet;
    }

    public void setReriewSet(Set<Reriew> reriewSet) {
        this.reriewSet = reriewSet;
    }

    public Reriew getReriewId() {
        return reriewId;
    }

    public void setReriewId(Reriew reriewId) {
        this.reriewId = reriewId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        if (!(object instanceof Reriew)) {
            return false;
        }
        Reriew other = (Reriew) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ou.demo.pojos.Reriew[ id=" + id + " ]";
    }

}
