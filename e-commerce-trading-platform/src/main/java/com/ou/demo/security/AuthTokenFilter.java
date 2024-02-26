
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.security;

import com.ou.demo.service.Users.DTO.JwtResponse;
import com.ou.demo.service.Users.DTO.UsersDto;
import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import com.ou.demo.service.Users.IUserService;

@RequiredArgsConstructor
@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private IUserService UserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        // get token from request header
        String token = getTokenFromRequest(request);

        if (token != null && jwtUtils.isTokenExpired(token)) {
            String refreshToken = request.getHeader("Refresh-token");

            // chỗ này đang dư thừa
            JwtResponse jwtResponse = jwtUtils.refreshToken(refreshToken);

        } else {
            // validate token
            if (StringUtils.hasText(token) && jwtUtils.validateToken(token)) {
                // get username from token
                String username = jwtUtils.getUsername(token);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    String userId = getUserDetails(token);

                    UserDetails userDetails = this.UserService.loadUserByUsername(username);
                    UsersDto user = UserService.getUserDetails(userId);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            user, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;

    }

    private String getUserDetails(String token) {
        String[] jwtSubject = jwtUtils.getSubject(token).split(",");
        return jwtSubject[0];
    }
}
