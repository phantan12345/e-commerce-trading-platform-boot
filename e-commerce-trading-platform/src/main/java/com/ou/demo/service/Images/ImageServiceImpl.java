/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Images;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ou.demo.service.Images.ImageService;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ADMIN
 */
@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private Cloudinary Cloudinary;

    @Override
    public Map Cloudinary(MultipartFile file) {
        try {
            Map res = this.Cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            return res;

        } catch (IOException ex) {
            Logger.getLogger(Cloudinary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  null;
    }
    
}
