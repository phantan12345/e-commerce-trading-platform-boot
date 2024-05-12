/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.service.Shipment.DTO.ShipmentDto;
import com.ou.demo.service.Shipment.IShipmentService;
import com.ou.demo.service.Users.DTO.CurrentUser;
import com.ou.demo.service.Users.DTO.UsersDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ShipmentController {

    private IShipmentService shipmentService;

    @GetMapping("/shipment")
    public ResponseEntity<?> getAllByCurrentUser(@CurrentUser UsersDto user) {
        return new ResponseEntity<>(shipmentService.getListShipmentByCurrenUser(user.getId()), HttpStatus.OK);
    }

    @PostMapping("/shipment")
    public ResponseEntity<?> doAction(@RequestBody ShipmentDto dto) {
        return new ResponseEntity<>(shipmentService.doAction(dto), HttpStatus.OK);
    }
}
