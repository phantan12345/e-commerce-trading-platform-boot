/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Address;

import com.ou.demo.pojos.Address;
import com.ou.demo.pojos.User;
import com.ou.demo.repositories.AddressRepository;
import com.ou.demo.service.Address.DTO.AddressDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class AddressService implements IAddressService {

    @Autowired
    private AddressRepository AddressRepository;

    @Override
    public Address addAddress(User user, AddressDto input) {
        Address a = new Address();
        a.setName(input.getName());
        a.setUserId(user);
        return AddressRepository.save(a);
    }

    @Override
    public Boolean deleteAddress(int id) {
        try {
            Address address = AddressRepository.findById(id).get();
            AddressRepository.delete(address);
            return true;
        } catch (Exception e) {
            return false;

        }

    }

    @Override
    public Address updateAddress(AddressDto input) {
        Address address = AddressRepository.findById(input.getId()).get();
        address.setName(input.getName());
        return AddressRepository.save(address);
    }

    @Override
    public List<Address> getAddresses(User user) {
        return AddressRepository.findAddressesCurentUser(user);
    }

}
