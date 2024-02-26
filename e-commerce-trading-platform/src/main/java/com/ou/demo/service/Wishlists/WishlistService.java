/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Wishlists;

import com.ou.demo.pojos.Wishlist;
import com.ou.demo.service.Wishlists.DTO.WishlistDTO;
import com.ou.demo.util.Service.Crud;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class WishlistService extends Crud<Wishlist, WishlistDTO> implements IWishlistService{
    
}
