package com.ou.demo.service.Reviews.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.Review;
import com.ou.demo.pojos.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ADMIN
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {

    public Integer id;
    public String coment;
    public Date dateContent;
    public Integer evaluate;
    public boolean isDelete;
    public Product productId;
    public List<Review> reply;
}
