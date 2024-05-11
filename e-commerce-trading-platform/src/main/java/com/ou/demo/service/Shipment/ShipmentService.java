/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Shipment;

import com.ou.demo.repositories.ShipmentReponsitory;
import java.io.Serial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class ShipmentService implements IShipmentService{
 
    @Autowired
    private ShipmentReponsitory shipmentReponsitory;
}
