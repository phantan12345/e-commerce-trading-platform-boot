/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

/**
 *
 * @author ADMIN
 */
public interface PaypalService {

    Payment createPayment(
            Double total,
            String currency,
            String method,
            String intent,
            String description,
            String cancelUrl,
            String successUrl) throws PayPalRESTException;

    Payment executePayment(String paymentId, String payerId)throws PayPalRESTException;
}
