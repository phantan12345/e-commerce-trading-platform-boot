/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Mails;

import com.ou.demo.pojos.Store;
import com.ou.demo.pojos.User;
import com.ou.demo.service.Mails.DTO.Mail;

/**
 *
 * @author ADMIN
 */
public interface MailService {

    public void sendEmail(User user, Mail mail);

    public void sendEmailStore(Store store, Mail mail);

}
