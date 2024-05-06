/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.GHN;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class GHNservice {

    @Value("${ghn.api.url}")
    private String urlService;

    @Value("${ghn.api.token}")
    private String apiToken;
}
