package com.example.shop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberMobile;
//    private String memberPostcode;
//    private String memberAddress;
//    private String memberDetailAddress;
    private int memberRole;
    private LocalDateTime memberCreatedTime;
    private LocalDateTime memberUpdatedTime;

}
