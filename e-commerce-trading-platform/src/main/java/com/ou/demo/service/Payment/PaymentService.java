/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Payment;

import com.ou.demo.pojos.Payment;
import com.ou.demo.service.Payment.DTO.PaymentDto;
import com.ou.demo.util.Service.Crud;
import com.ou.demo.util.Service.ICrud;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class PaymentService  extends Crud<Payment, PaymentDto> implements IPaymentService{
    
}
