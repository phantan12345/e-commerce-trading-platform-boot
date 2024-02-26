
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.security;

import com.ou.demo.service.Users.DTO.JwtResponse;
import com.ou.demo.exceptions.GoodNewsApiException;
import com.ou.demo.pojos.User;
import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import com.ou.demo.service.Users.IUserService;

@Component
public class JwtUtils implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    private static final int REFRESH_TOKEN_EXPIRATION_DATE = -30 * 24 * 60 * 60 * 1000;

    @Value("${bezkoder.app.jwtSecret}")
    private String jwtSecret;

    @Value("${bezkoder.app.jwtExpirationMs}")
    private long jwtExpirationMs;

    @Autowired
    private IUserService userService;

    // generate JWT token
    public JwtResponse generateToken(UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = userService.findByUsername(username);
        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtExpirationMs);

        String accessToken = Jwts.builder()
                .setSubject(username)
                .claim("email", user.getEmail())
                .claim("role", user.getRoleId())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();

        String refreshToken = Jwts.builder()
                .setSubject(username)
                .claim("email", user.getEmail())
                .claim("role", user.getRoleId())
                .setIssuedAt(currentDate)
                .setExpiration(new Date(currentDate.getTime() + REFRESH_TOKEN_EXPIRATION_DATE))
                .signWith(key())
                .compact();

        JwtResponse jwtResponse = new JwtResponse(accessToken, refreshToken, "Bearer");

        return jwtResponse;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));

    }

    // get username from JWT token
    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();
        return username;
    }

    // validate token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new GoodNewsApiException(HttpStatus.BAD_REQUEST, "Expired JWT Token");
        } catch (MalformedJwtException e) {
            throw new GoodNewsApiException(HttpStatus.BAD_REQUEST, "Invalid JWT Token");
        } catch (UnsupportedJwtException e) {
            throw new GoodNewsApiException(HttpStatus.BAD_REQUEST, "Unsupported JWT Token");
        } catch (IllegalArgumentException e) {
            throw new GoodNewsApiException(HttpStatus.BAD_REQUEST, "JWT Claims string is empty");
        }
    }

    public boolean isTokenExpired(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();

        Date expirationDate = claims.getExpiration();
        Date currentDate = new Date();
        return expirationDate.before(currentDate);
    }

    public JwtResponse refreshToken(String refreshToken) {
        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtExpirationMs);
        if (validateToken(refreshToken)) {
            try {
                String username = getUsername(refreshToken);
                User user = userService.findByUsername(username);
                String accessToken = Jwts.builder()
                        .setSubject(username)
                        .claim("email", user.getEmail())
                        .claim("role", user.getRoleId())
                        .setIssuedAt(new Date())
                        .setExpiration(expireDate)
                        .signWith(key())
                        .compact();

                JwtResponse jwtResponse = new JwtResponse(accessToken, refreshToken, "Bearer");
                return jwtResponse;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("Refresh token is invalid");
        }
        return null;
    }

    public String getSubject(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

}
