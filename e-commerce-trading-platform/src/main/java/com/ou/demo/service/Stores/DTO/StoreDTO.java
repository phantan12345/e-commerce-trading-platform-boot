package com.ou.demo.service.Stores.DTO;

import com.ou.demo.pojos.User;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class StoreDTO {

    public String name;
    public String address;
    public  User UserId;
}
