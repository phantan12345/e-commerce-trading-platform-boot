/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Evaluate.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.User;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 *
 * @author ADMIN
 */
@Data
public class EvaluateDto {

    public Integer id;
    public double evaluate;
    public Product ProductId;
    public User UserId;
}
