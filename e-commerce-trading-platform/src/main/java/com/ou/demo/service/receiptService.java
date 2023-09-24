/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service;

import com.ou.demo.dto.CartDto;
import com.ou.demo.pojos.User;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
public interface receiptService {

    Object addReceipt(Map<String, CartDto> carts, User user);

}
