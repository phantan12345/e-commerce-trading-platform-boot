/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.util;


import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class Utils {
    @Autowired
    private Environment env;

    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public Map<String,String> getAllErrorValidation(BindingResult result){
        try {
            Map<String, String> errors = new HashMap<>();
            result.getAllErrors().forEach((error) ->{

                String fieldName = ((FieldError) error).getField();
                String message = "";
                if (error.getDefaultMessage() != null){
                    message = error.getDefaultMessage();
                }else {
                    message = env.getProperty(error.getCode());
                }
                errors.put(fieldName, message);
            });
            return errors;
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }
    public DateFormat getDateFormatter(){

        return formatter;
    }
    public String randCodeConfirm(){
        try {
            double randomDouble = Math.random();
            randomDouble = randomDouble * 100000 + 1;
            return String.format("%06d",(int)randomDouble);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }

    public String generatePromotionCode(String prefix){
        try {
            double randomDouble = Math.random();
            randomDouble = randomDouble * 1000 + 1;
            return prefix + String.format("%04d",(int)randomDouble);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }

    public String passwordEncoder(String password){
        try {
            return org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }
    public SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }
    public List<Object[]> customListStatsMonth(List<Object[]> list) {
        try {
            boolean flag = false;
            if (list != null) {
                for (int i = 1; i <= 12; i++) {
                    for (int j = 0; j < list.size(); j++) {
                        if (i == (int) list.get(j)[0]) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag == false) {
                        Object[] term = {i, 0};
                        list.add(term);
                    }
                    flag = false;
                }
                Collections.sort(list, (Object[] a1, Object[] a2) -> (int) a1[0] - (int) a2[0]);
            }
            return list;
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return null;

    }

    public List<Object[]> customListStatsQuarter(List<Object[]> list) {
        try {
            boolean flag = false;
            if (list != null) {
                for (int i = 1; i <= 4; i++) {
                    for (int j = 0; j < list.size(); j++) {
                        if (i == (int) list.get(j)[0]) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag == false) {
                        Object[] term = {i, 0};
                        list.add(term);
                    }
                    flag = false;
                }
                Collections.sort(list, (Object[] a1, Object[] a2) -> (int) a1[0] - (int) a2[0]);
            }
            return list;
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }
    public List<Object[]> customListStatsComment(List<Object[]> list) {
        try {
            boolean flag = false;
            if (list != null) {
                for (int i = 1; i <= 5; i++) {
                    for (int j = 0; j < list.size(); j++) {
                        if (i == (int) list.get(j)[0]) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag == false) {
                        Object[] term = {i, 0};
                        list.add(term);
                    }
                    flag = false;
                }
                Collections.sort(list, (Object[] a1, Object[] a2) -> (int) a1[0] - (int) a2[0]);
            }
            return list;
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }

  

    public String hmacWithApacheCommons(String algorithm, String data, String key) {
        try {
            String hmac = new HmacUtils(algorithm, key).hmacHex(data);
            return hmac;
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }

    public Boolean checkPhoneNumberIsValid(String phoneNumber){
        try {
            if (phoneNumber.startsWith("03") || phoneNumber.startsWith("05") || phoneNumber.startsWith("07")
                    || phoneNumber.startsWith("08") || phoneNumber.startsWith("09")){
                if (phoneNumber.length() == 10){
                    return true;
                }
            }
            return false;
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return false;
    }

    public String generateUUID() throws RuntimeException{
        try {
            UUID uuid = UUID.randomUUID();
            return uuid.toString();
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }



 
}