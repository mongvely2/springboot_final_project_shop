package com.example.shop.dto;

import com.example.shop.entity.MemberEntity;
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

    public static MemberDTO toDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberMobile(memberEntity.getMemberMobile());
        memberDTO.setMemberRole(memberEntity.getMemberRole());
        memberDTO.setMemberCreatedTime(memberEntity.getCreatedTime());
        memberDTO.setMemberUpdatedTime(memberEntity.getUpdatedTime());
        return memberDTO;
    }

}
