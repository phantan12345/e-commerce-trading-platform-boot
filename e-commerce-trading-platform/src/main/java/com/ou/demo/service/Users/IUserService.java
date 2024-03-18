package com.ou.demo.service.Users;

import com.ou.demo.pojos.User;
import com.ou.demo.service.Users.DTO.UsersDto;
import com.ou.demo.util.Service.ICrud;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService extends UserDetailsService ,ICrud<User, UsersDto>  {

    User findByUsername(String user);

    boolean addUsers(Map<String, String> params, MultipartFile file);

    User updateActice(int id);

    User findById(int id);

    User update(User user);


    User addAdmin();

    List<User> getRequestment();

    UsersDto getUserDetails(String username);

}
