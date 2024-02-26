/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Messsages.DTO;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageSummaryDto {

    private Integer userId;
    private Integer correspondUserId;
    private Integer messageId;
    private String chatRecipientName;
    private String avatar;
    private String message;
    private String status;
    private Integer latestMessageUserId;

}
