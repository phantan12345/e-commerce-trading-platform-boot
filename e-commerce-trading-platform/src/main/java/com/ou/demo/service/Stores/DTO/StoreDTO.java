package com.ou.demo.service.Stores.DTO;

import com.ou.demo.pojos.Role;
import com.ou.demo.pojos.Store;
import com.ou.demo.pojos.User;
import com.ou.demo.service.Users.DTO.UsersDto;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StoreDTO {

    public String name;
    public String address;

}
