/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.impl;

import com.ou.demo.pojos.User;
import com.ou.demo.pojos.Voucher;
import com.ou.demo.repositories.UserRepository;
import com.ou.demo.service.UserService;
import com.ou.demo.service.UserVoucherService;
import com.ou.demo.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class UserVoucherServiceImpl implements UserVoucherService{

    @Autowired
    private VoucherService VoucherService;

    @Autowired
    private UserService UserService;

    @Override
    public User addVoucherUser(User user, Voucher voucher) {
        return UserService.update(user);

    }
}
