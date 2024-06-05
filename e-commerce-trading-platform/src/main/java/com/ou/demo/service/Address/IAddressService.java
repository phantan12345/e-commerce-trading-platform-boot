/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ou.demo.service.Address;

import com.ou.demo.pojos.Address;
import com.ou.demo.pojos.User;
import com.ou.demo.service.Address.DTO.AddressDto;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IAddressService {
    Address addAddress(User user,AddressDto input);
    
    Boolean deleteAddress(int id);
    Address updateAddress(AddressDto input);
    List<Address> getAddresses(User input);

}
