/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ou.demo.service.Evaluate;

import com.ou.demo.pojos.Evaluate;
import com.ou.demo.service.Evaluate.DTO.EvaluateDto;
import com.ou.demo.util.Service.ICrud;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IEvaluateService extends ICrud<Evaluate,EvaluateDto > {
    
    List<Evaluate> findByProductId(int id);
}
