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
    private String title;
    private String avatar;
    private String subtitle;
    private int unread;
    private Integer latestMessageUserId;

}
