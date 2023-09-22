/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.dto.Login;
import com.ou.demo.pojos.User;
import com.ou.demo.security.JwtUtils;
import com.ou.demo.service.StoreService;
import com.ou.demo.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author ADMIN
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private UserService UserService;

    private JwtUtils jwtUtils;

    private AuthenticationManager authenticationManager;

    private HttpServletResponse response;

    @PostMapping("/signup/")
    public ResponseEntity<?> registerUser(@Valid @RequestParam Map<String, String> params, @RequestPart MultipartFile file) {
        UserService.addUsers(params, file);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping(path = "/signin/")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody Login login) throws Exception {

        authenticate(login.getUsername(), login.getPassword());
        final UserDetails userDetails = UserService.loadUserByUsername(login.getUsername());
        User user = UserService.findByUsername(userDetails.getUsername());

        String jwtResponse = jwtUtils.generateJwtToken(userDetails);

        return ResponseEntity.ok().body(jwtResponse);

    }

    private void authenticate(String username, String password) throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @GetMapping("/current-user/")
    @CrossOrigin
    public ResponseEntity<User> details(Principal user) {

        if (user != null) {
            User u = this.UserService.findByUsername(user.getName());
            return new ResponseEntity<>(u, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

   

}
