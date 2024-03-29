/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Vouchers;

import com.ou.demo.pojos.Voucher;
import com.ou.demo.repositories.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class VoucherServiceImpl implements VoucherService{

    @Autowired
    private VoucherRepository VoucherRepository;
    
    @Override
    public Voucher create(Voucher vou) {
        return VoucherRepository.save(vou);
    }

    @Override
    public Voucher findByid(int id) {
        return VoucherRepository.findById(id).get();
    }

    @Override
    public Voucher findByCode(String code) {
        return VoucherRepository.findByCode(code);
    }
    
}
