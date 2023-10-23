/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service;

import com.ou.demo.pojos.User;
import com.ou.demo.pojos.Voucher;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
public interface UserVoucherService {

    User addVoucherUser(User user, Voucher voucher);

}
