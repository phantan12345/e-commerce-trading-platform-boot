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

    private Order1 orderId;
    private String provider;
    private String productName;
    private int quatity;
    private String url;

    public ShipmentDto() {
    }

    public ShipmentDto(Integer id, String address, String active, Order1 orderId, String provider, String productName, int quatity, String url) {
        this.id = id;
        this.address = address;
        this.active = active;
        this.orderId = orderId;
        this.provider = provider;
        this.productName = productName;
        this.quatity = quatity;
        this.url = url;
    }

}
