/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ou.demo.pojos.Role;
import com.ou.demo.pojos.User;
import com.ou.demo.repositories.RoleReponsitory;
import com.ou.demo.repositories.UserRepository;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("userDetailsService")
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Cloudinary Cloudinary;

    @Autowired
    private RoleService roleService;

    public User findByUsername(String user) {
        return UserRepository.findByUsername(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User users = UserRepository.findByUsername(username);

        if (users == null) {
            throw new UsernameNotFoundException("Không tồn tại!");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        Optional<Role> role = roleService.getRoleById(users.getRoleId().getId());
        String roleName=role.get().getRoleName();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        
        return new org.springframework.security.core.userdetails.User(
                users.getUsername(), users.getPassword(), authorities);
    }

    public User addUsers(Map<String, String> params, MultipartFile file) {
        User u = new User();
        u.setUsername(params.get("username"));
        u.setPassword(this.passwordEncoder.encode(params.get("password")));

        u.setEmail(params.get("phone"));
        u.setEmail(params.get("email"));
        u.setActive(Boolean.FALSE);

        try {
            Map res = this.Cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            u.setAvatar(res.get("secure_url").toString());

        } catch (IOException ex) {
            Logger.getLogger(Cloudinary.class.getName()).log(Level.SEVERE, null, ex);
        }

        return UserRepository.save(u);
    }

}
