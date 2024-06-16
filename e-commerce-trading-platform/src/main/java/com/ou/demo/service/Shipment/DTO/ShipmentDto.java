/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Shipment.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ou.demo.pojos.Order1;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author ADMIN
 */
@Setter
@Getter
@Builder
public class ShipmentDto {

    private Integer id;

    private String address;

    private String active;

    private String provider;
    private String productName;
    private int productId;
    private int quatity;
    private String url;
    private BigDecimal total;

    public ShipmentDto() {
    }

    public ShipmentDto(Integer id, String address, String active, String provider, String productName,int productId, int quatity, String url, BigDecimal total) {
        this.id = id;
        this.address = address;
        this.active = active;
        this.provider = provider;
        this.productName = productName;
        this.productId=productId;
        this.quatity = quatity;
        this.url = url;
        this.total=total;
    }

}
