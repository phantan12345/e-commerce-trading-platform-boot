/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Vouchers;

import com.ou.demo.pojos.Voucher;

/**
 *
 * @author ADMIN
 */
public interface VoucherService {

    Voucher create(Voucher vou);

    Voucher findByid(int id);
    Voucher findByCode(String code);
}
