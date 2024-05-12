/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ou.demo.service.Shipment;

import com.ou.demo.pojos.Shipment;
import com.ou.demo.service.Shipment.DTO.ShipmentDto;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IShipmentService {

    List<Shipment> getListShipmentByCurrenUser(int id);

    Shipment doAction(ShipmentDto dto);

}
