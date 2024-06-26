/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Stores;

import com.ou.demo.pojos.Store;
import com.ou.demo.pojos.User;
import com.ou.demo.service.Stores.DTO.StoreDTO;
import com.ou.demo.util.Service.ICrud;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IStoreService extends ICrud<Store, StoreDTO> {

    Store Create(StoreDTO store, User u);

    Store findStoreById(int id);

    StoreDTO findStoreDTOById(int id);

    List<Store> getStores();

}
