/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Receipts;

import com.ou.demo.pojos.Order1;
import com.ou.demo.service.Receipts.DTO.CartDto;
import com.ou.demo.service.Receipts.DTO.CartInput;
import com.ou.demo.pojos.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
public interface IReceiptService {

    Order1 addReceipt(CartInput carts);
    
    

}
