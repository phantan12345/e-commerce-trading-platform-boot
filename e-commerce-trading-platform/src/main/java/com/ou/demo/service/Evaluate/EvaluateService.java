/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Evaluate;

import com.ou.demo.pojos.Evaluate;
import com.ou.demo.repositories.EvaluateRespository;
import com.ou.demo.service.Evaluate.DTO.EvaluateDto;
import com.ou.demo.util.Service.Crud;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class EvaluateService extends Crud<Evaluate, EvaluateDto> implements IEvaluateService {
    
    @Autowired
    private EvaluateRespository EvaluateRespository;
    
    @Override
    public List<Evaluate> findByProductId(int id) {
        return EvaluateRespository.findByProductId(id);
    }
    
}
