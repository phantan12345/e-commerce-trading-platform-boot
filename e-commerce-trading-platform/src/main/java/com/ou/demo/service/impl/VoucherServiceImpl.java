/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.impl;

import com.ou.demo.pojos.Voucher;
import com.ou.demo.repositories.VoucherRepository;
import com.ou.demo.service.VoucherService;
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
    
}
