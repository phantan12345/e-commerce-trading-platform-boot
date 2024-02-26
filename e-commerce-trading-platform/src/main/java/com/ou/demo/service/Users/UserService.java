/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Users;

import com.ou.demo.service.Images.ImageServiceImpl;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ou.demo.exceptions.ResourceNotFoundException;
import com.ou.demo.service.Mails.DTO.Mail;
import com.ou.demo.pojos.Role;
import com.ou.demo.pojos.Store;
import com.ou.demo.pojos.User;
import com.ou.demo.pojos.Voucher;
import com.ou.demo.repositories.StoreReponsitory;
import com.ou.demo.repositories.UserRepository;
import com.ou.demo.service.Mails.MailService;
import com.ou.demo.service.Stores.StoreService;
import com.ou.demo.service.Vouchers.VoucherService;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ou.demo.service.Roles.IRoleService;
import com.ou.demo.service.Users.DTO.UsersDto;
import org.modelmapper.ModelMapper;

/**
 *
 * @author ADMIN
 */
@Service("userDetailsService")
public class UserService implements IUserService {

    @Autowired
    private MailService MailService;

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private ImageServiceImpl imageService;

    @Autowired
    private ModelMapper mapper;

//    @Autowired
//    private StoreService StoreService;
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
        String roleName = role.get().getRoleName();
        authorities.add(new SimpleGrantedAuthority(roleName));

        return new org.springframework.security.core.userdetails.User(
                users.getUsername(), users.getPassword(), authorities);
    }

    @Override
    public boolean addUsers(Map<String, String> params, MultipartFile file) {

        User u = new User();
        u.setUsername(params.get("username"));
        u.setPassword(this.passwordEncoder.encode(params.get("password")));
        u.setEmail(params.get("email"));
        u.setPhone(params.get("phone"));
        u.setRoleId(roleService.findRoleByRoleName("USER"));

        u.setActive(Boolean.TRUE);
        u.setAvatar(imageService.Cloudinary(file).get("secure_url").toString());
        User user1 = u;
        User user = UserRepository.save(u);
        if (user != null) {
            Mail mail = new Mail();
            mail.setMailFrom("2051050435tan@ou.edu.vn");
            mail.setMailTo(user.getEmail());
            mail.setMailSubject("Spring Boot - Email Register");
            mail.setMailContent("BẠN ĐÃ ĐĂNG KÍ THÀNH CÔNG");
            MailService.sendEmail(user,mail);
            return true;
        }

        return false;

    }

    @Override
    public User updateActice(int id) {

        User user = UserRepository.findById(id).get();
        try {
            if (user.getRoleId().getRoleName().equals(roleService.findRoleByRoleName("USER").getRoleName())) {
                user.setRoleId(roleService.findRoleByRoleName("SALER"));
                return UserRepository.save(user);
            }
        } catch (Exception e) {

        }
        return null;

    }

    @Override
    public User findById(int id) {
        return UserRepository.findById(id).get();
    }

    @Override
    public User update(User user) {
        try {
            if (user != null) {
                return UserRepository.save(user);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    @Override
    public List<User> listUser() {
        return UserRepository.findAll();
    }

    @Override
    public User addAdmin() {
        User u = new User();
        u.setUsername("admin");
        u.setPassword(this.passwordEncoder.encode("admin"));
        u.setEmail("admin@gmail.com");
        u.setPhone("037274593");
        u.setRoleId(roleService.findRoleByRoleName("ADMIN"));

        u.setActive(Boolean.TRUE);

        return UserRepository.save(u);
    }

    @Override
    public UsersDto getUserDetails(String username) {
        UsersDto dto = mapper.map(UserRepository.findByUsername(username), UsersDto.class);

        dto.setRoleId(UserRepository.findByUsername(username).getRoleId().getRoleName());
        return dto;
    }

}
