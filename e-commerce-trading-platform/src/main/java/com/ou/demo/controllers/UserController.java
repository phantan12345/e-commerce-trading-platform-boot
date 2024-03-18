/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.service.Users.DTO.JwtResponse;
import com.ou.demo.service.Users.DTO.Login;
import com.ou.demo.pojos.Store;
import com.ou.demo.pojos.User;
import com.ou.demo.pojos.Voucher;
import com.ou.demo.security.JwtUtils;
import com.ou.demo.service.Mails.DTO.Mail;
import com.ou.demo.service.Mails.MailService;
import com.ou.demo.service.Users.DTO.CurrentUser;
import com.ou.demo.service.Users.DTO.UsersDto;
import com.ou.demo.service.Vouchers.VoucherService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.ou.demo.service.Users.IUserService;
import com.ou.demo.service.Stores.IStoreService;
import com.ou.demo.service.Stores.StoreService;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
 *
 * @author ADMIN
 */
@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private MailService MailService;

    private IUserService UserService;

    private JwtUtils jwtUtils;

    private AuthenticationManager authenticationManager;

    private StoreService StoreService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestParam Map<String, String> params, @RequestPart MultipartFile file) {
        boolean user = UserService.addUsers(params, file);
        return new ResponseEntity<>(user == false ? "error User registered successfully!" : new ResponseEntity<>(user, HttpStatus.OK),
                HttpStatus.OK);
    }

    @PostMapping(path = "/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody Login login, BindingResult bindingResult) throws Exception {
        final UserDetails userDetails = UserService.loadUserByUsername(login.getUsername());

        authenticate(login.getUsername(), login.getPassword());

        JwtResponse jwtResponse = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok().body(jwtResponse);

    }

    private void authenticate(String username, String password) throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @GetMapping("/current-user")
    public ResponseEntity<?> details(@CurrentUser UsersDto user) {

        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(UserService.getAll(), HttpStatus.OK);

    }

    @PostMapping("/admin")
    public ResponseEntity<?> registerAdmin() {
        User user = UserService.addAdmin();
        return new ResponseEntity<>(user == null ? "error User registered successfully!" : new ResponseEntity<>(user, HttpStatus.OK),
                HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(UserService.findById(id),
                HttpStatus.OK);
    }

    @GetMapping("/requestment")
    public ResponseEntity<?> getRequestment() {
        return new ResponseEntity<>(UserService.getRequestment(),
                HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(UserService.Delete(id),
                HttpStatus.OK);
    }

    @PostMapping("/requestment/{id}")
    public ResponseEntity<?> requestment(@PathVariable(value = "id") int id) {

        User user = UserService.findById(id);
        user.setActive(Boolean.FALSE);
        user = UserService.update(user);
        if (user != null) {
            if (user != null) {
                Mail mail = new Mail();
                mail.setMailFrom("2051050435tan@ou.edu.vn");
                mail.setMailTo(user.getEmail());
                mail.setMailSubject("Spring Boot - Email Register");
                mail.setMailContent("BẠN ĐÃ ĐĂNG KÍ THÀNH CÔNG");

                MailService.sendEmailStore(StoreService.findStoreByUserID(user), mail);
            }
            return new ResponseEntity<>(user == null ? "orror find products"
                    : new ResponseEntity(user, HttpStatus.NOT_MODIFIED), HttpStatus.OK);

        }
        return new ResponseEntity<>("no find store", HttpStatus.BAD_REQUEST);

    }

}
