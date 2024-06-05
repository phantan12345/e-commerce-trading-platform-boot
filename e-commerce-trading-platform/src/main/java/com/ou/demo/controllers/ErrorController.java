/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.controllers;

import com.ou.demo.exceptions.GoodNewsApiException;
import com.ou.demo.exceptions.ShipmentException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author ADMIN
 */
@ControllerAdvice
@ResponseBody
public class ErrorController {

    @ExceptionHandler(ShipmentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleAssertionError(Exception  ex) {
        return new ResponseEntity<>( new GoodNewsApiException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
