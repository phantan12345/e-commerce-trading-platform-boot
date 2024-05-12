/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Shipment;

import com.ou.demo.pojos.Shipment;
import com.ou.demo.repositories.ShipmentReponsitory;
import com.ou.demo.service.Shipment.DTO.ShipmentDto;
import java.io.Serial;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class ShipmentService implements IShipmentService {

    @Autowired
    private ShipmentReponsitory shipmentReponsitory;

    @Override
    public List<Shipment> getListShipmentByCurrenUser(int id) {
        return shipmentReponsitory.findShipmentByCurrntUser(id);
    }

    @Override
    public Shipment doAction(ShipmentDto dto) {
        Shipment shipment = new Shipment(dto.getId());

        switch (dto.getProvider()) {
            case "Accepted":
                shipment.setActive("Accepted");
                break;
            case "Packed":
                shipment.setActive("Packed");
                break;
            case "Completed":
                shipment.setActive("Completed");
                break;
            default:
                throw new AssertionError();
        }
        return shipmentReponsitory.save(shipment);
    }
}
