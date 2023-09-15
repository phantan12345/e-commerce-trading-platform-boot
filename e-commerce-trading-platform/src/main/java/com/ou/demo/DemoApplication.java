package com.ou.demo;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ou.demo.dto.Mail;
import com.ou.demo.service.MailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@SpringBootApplication
public class DemoApplication  {


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "ddznsqfbo",
                "api_key", "654246967571578",
                "api_secret", "Kpyhy80PQBnqlnI7EpI3kyxbXrs",
                "secure", true));
        return cloudinary;
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

//    @Override
//    public void run(String... args) throws Exception {
//        Mail mail = new Mail();
//        mail.setMailFrom("2051050435tan@ou.edu.vn");
//        mail.setMailTo("phannhuttan2002@gmail.com");
//        mail.setMailSubject("Spring Boot - Email demo");
//        mail.setMailContent("Just testing");
//        mailService.sendEmail(mail);
//    }
}
