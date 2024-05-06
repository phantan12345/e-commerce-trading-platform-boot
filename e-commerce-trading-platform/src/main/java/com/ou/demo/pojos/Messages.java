/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.pojos;

import java.io.Serializable;
import jakarta.persistence.*;

import javax.xml.bind.annotation.XmlRootElement;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "messages")
@Data
@Builder
public class Messages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "message")
    private String message;
    @Column(name = "status")
    private String status;

    @JoinColumn(name = "sent_by", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User sentBy;
    @JoinColumn(name = "sent_to", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User sentTo;

    public Messages() {
    }

    public Messages(Integer id, String message, String status, User sentBy, User sentTo) {
        this.id = id;
        this.message = message;
        this.status = status;
        this.sentBy = sentBy;
        this.sentTo = sentTo;
    }

   
    
}
