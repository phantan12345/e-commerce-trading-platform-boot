/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.util.Service;

import java.util.List;


public interface ICrud<T extends Object, D extends Object> {

    public T Create(D Dto);

    public T Update(D Dto,int id);

    public T Delete(int id);

    public List<T> getAll();
}
