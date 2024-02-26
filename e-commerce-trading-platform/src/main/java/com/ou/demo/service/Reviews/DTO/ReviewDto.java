
package com.ou.demo.service.Reviews.DTO;

import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.Review;
import com.ou.demo.pojos.User;
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

    private int id;
    @NotBlank
    private String coment;
    @NotBlank
    private User user;
    @NotBlank
    private Product product;
    private Date dateContent;
    @NotBlank
    private double evaluate;
    private Set<Review> listReply;
}
