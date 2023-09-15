
package com.ou.demo.dto;

import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.User;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
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
    private String text;
    @NotBlank
    private User user;
    @NotBlank
    private Product product;
    private Date date;
    @NotBlank
    private double evaluate;
//    private List<CommentDto> listReply;
}
