/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Mails;

import com.ou.demo.pojos.Store;
import com.ou.demo.pojos.User;
import com.ou.demo.service.Mails.DTO.Mail;
import com.ou.demo.service.Stores.StoreService;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Override
    public void sendEmail(User user, Mail mail) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("2051050435tan@ou.edu.vn", "0372745193");
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mail.getMailFrom()));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mail.getMailTo())
            );
            message.setSubject(mail.getMailSubject());

            // Nội dung của email là HTML
            String htmlContent = "<p><b>Kính gửi Anh/Chị:</b></p>\n"
                    + "<p>Store của quý khách đã được phê duyệt!!!! </p>\n"
                    + "<table cellpadding=\"8\" cellspacing=\"0\"  width=\"100%\">\n"
                    + "   <tr>\n"
                    + "        <td style=\"border: 1px solid #EBEDF3;\n"
                    + "                       background-color: #f2f2f2 !important;\" width=170>\n"
                    + "            username\n"
                    + "        </td>\n"
                    + "        <td style=\"border: 1px solid #EBEDF3;\">\n"
                    + user.getUsername()
                    + "        </td>\n"
                    + "    </tr>\n"
                    + "   \n"
                    + "     <tr>\n"
                    + "        <td style=\"border: 1px solid #EBEDF3;\n"
                    + "                       background-color: #f2f2f2 !important;\">\n"
                    + "            password\n"
                    + "        </td>\n"
                    + "        <td style=\"border: 1px solid #EBEDF3;\">\n"
                    + user.getPassword()
                    + "        </td>\n"
                    + "    </tr>\n"
                    + "    <tr>\n"
                    + "        <td style=\"border: 1px solid #EBEDF3;\n"
                    + "                       background-color: #f2f2f2 !important;\">\n"
                    + "            Để đăng nhập\n"
                    + "        </td>\n"
                    + "        <td style=\"border: 1px solid #EBEDF3; padding-top: 15px; padding-bottom: 15px;\">\n"
                    + "            <a href=\"http://localhost:8080/swagger-ui/index.html#/\" style=\"background-color: #03cb6e; padding: 10px; color: #fff; border-radius: 0.42rem; \">\n"
                    + "                <strong>Vui lòng bấm vào đây</strong>\n"
                    + "            </a>\n"
                    + "        </td>\n"
                    + "    </tr>\n"
                    + "</table>\n"
                    + "<br>\n"
                    + "<p>\n"
                    + "    Trân trọng,<br><br>\n"
                    + "    <b>MAIL JAVA</b><br><br>\n"
                    + "    <i>Email này được gửi tự động từ hệ thống Mail Java, vui lòng không phản hồi lại.</i>\n"
                    + "</p>";

            message.setContent(htmlContent, mail.getContentType());

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sendEmailStore(Store store, Mail mail) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("2051050435tan@ou.edu.vn", "0372745193");
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mail.getMailFrom()));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mail.getMailTo())
            );
            message.setSubject(mail.getMailSubject());

            // Nội dung của email là HTML
            String htmlContent = "<p><b>Kính gửi Anh/Chị:</b></p>\n"
                    + "<p>Tài khoản của quý khách đã được đăng ký thành công!!!! </p>\n"
                    + "<table cellpadding=\"8\" cellspacing=\"0\"  width=\"100%\">\n"
                    + "   <tr>\n"
                    + "        <td style=\"border: 1px solid #EBEDF3;\n"
                    + "                       background-color: #f2f2f2 !important;\" width=170>\n"
                    + "            store name\n"
                    + "        </td>\n"
                    + "        <td style=\"border: 1px solid #EBEDF3;\">\n"
                    + store.getUser().getName()
                    + "        </td>\n"
                    + "    </tr>\n"
                    + "   \n"
                    + "     <tr>\n"
                    + "        <td style=\"border: 1px solid #EBEDF3;\n"
                    + "                       background-color: #f2f2f2 !important;\">\n"
                    + "            Address\n"
                    + "        </td>\n"
                    + "        <td style=\"border: 1px solid #EBEDF3;\">\n"
                    + store.getAddress()
                    + "        </td>\n"
                    + "    </tr>\n"
                    + "    <tr>\n"
                    + "        <td style=\"border: 1px solid #EBEDF3;\n"
                    + "                       background-color: #f2f2f2 !important;\">\n"
                    + "            Để đăng nhập\n"
                    + "        </td>\n"
                    + "        <td style=\"border: 1px solid #EBEDF3; padding-top: 15px; padding-bottom: 15px;\">\n"
                    + "            <a href=\"http://localhost:8080/swagger-ui/index.html#/\" style=\"background-color: #03cb6e; padding: 10px; color: #fff; border-radius: 0.42rem; \">\n"
                    + "                <strong>Vui lòng bấm vào đây</strong>\n"
                    + "            </a>\n"
                    + "        </td>\n"
                    + "    </tr>\n"
                    + "</table>\n"
                    + "<br>\n"
                    + "<p>\n"
                    + "    Trân trọng,<br><br>\n"
                    + "    <b>MAIL JAVA</b><br><br>\n"
                    + "    <i>Email này được gửi tự động từ hệ thống Mail Java, vui lòng không phản hồi lại.</i>\n"
                    + "</p>";

            message.setContent(htmlContent, mail.getContentType());

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
