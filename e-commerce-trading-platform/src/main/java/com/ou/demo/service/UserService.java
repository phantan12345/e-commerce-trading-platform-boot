package com.ou.demo.service;

import com.ou.demo.pojos.User;
import com.ou.demo.pojos.Voucher;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

public interface UserService extends UserDetailsService {

    User findByUsername(String user);

    boolean addUsers(Map<String, String> params, MultipartFile file);

    User updateActice(int id);

    User findById(int id);
    
    User update(User user);
    

}
