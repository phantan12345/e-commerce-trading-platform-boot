/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Shipment;

import com.ou.demo.pojos.Order1;
import com.ou.demo.pojos.Shipment;
import com.ou.demo.repositories.ShipmentReponsitory;
import com.ou.demo.service.Messsages.DTO.MessageSummaryDto;
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
    public List<ShipmentDto> getListShipmentByCurrenUser(int id) {
        List<Object[]> dto = shipmentReponsitory.findShipmentByCurrntUser(id);

        return dto.stream()
                .map(shipmentDtoObject -> new ShipmentDto(
                Integer.valueOf(shipmentDtoObject[0].toString()),
                shipmentDtoObject[1].toString(),
                shipmentDtoObject[2].toString(),
                new Order1(Integer.valueOf(shipmentDtoObject[3].toString())),
                shipmentDtoObject[2].toString(),
                shipmentDtoObject[4].toString(),
                Integer.valueOf(shipmentDtoObject[5].toString()),
                shipmentDtoObject[6].toString()))
                .toList();
    }

    @Override
    public Shipment doAction(ShipmentDto dto) {
        Shipment shipment = shipmentReponsitory.findById(dto.getId()).get();

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

    @Override
    public List<ShipmentDto> getListShipmentByCurrenStore() {
        List<Object[]> dto = shipmentReponsitory.findShipmentByAdmin();

        return dto.stream()
                .map(shipmentDtoObject -> new ShipmentDto(
                Integer.valueOf(shipmentDtoObject[0].toString()),
                shipmentDtoObject[1].toString(),
                shipmentDtoObject[2].toString(),
                new Order1(Integer.valueOf(shipmentDtoObject[3].toString())),
                shipmentDtoObject[2].toString(),
                shipmentDtoObject[4].toString(),
                Integer.valueOf(shipmentDtoObject[5].toString()),
                shipmentDtoObject[6].toString()))
                .toList();
    }
}
