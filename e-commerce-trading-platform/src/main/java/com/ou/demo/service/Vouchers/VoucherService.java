/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Vouchers;

import com.ou.demo.pojos.Voucher;
import com.ou.demo.service.Vouchers.DTO.VoucherDto;
import com.ou.demo.util.Service.ICrud;

/**
 *
 * @author ADMIN
 */
public interface VoucherService extends ICrud<Voucher, VoucherDto>{


    Voucher findByid(int id);
    Voucher findByCode(String code);
}
