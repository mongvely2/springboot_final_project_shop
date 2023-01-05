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
    private String memberPostcode;
    private String memberAddress;
    private String memberDetailAddress;
    private String memberReference;
    private int memberRole;
    private LocalDateTime memberCreatedTime;
    private LocalDateTime memberUpdatedTime;

    public MemberDTO(Long id, String memberEmail, String memberPassword, String memberName, String memberMobile, String memberPostcode, String memberAddress, String memberDetailAddress, String memberReference, int memberRole, LocalDateTime memberCreatedTime, LocalDateTime memberUpdatedTime) {
        this.id = id;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberMobile = memberMobile;
        this.memberPostcode = memberPostcode;
        this.memberAddress = memberAddress;
        this.memberDetailAddress = memberDetailAddress;
        this.memberReference = memberReference;
        this.memberRole = memberRole;
        this.memberCreatedTime = memberCreatedTime;
        this.memberUpdatedTime = memberUpdatedTime;
    }


    public static MemberDTO toDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberMobile(memberEntity.getMemberMobile());
        memberDTO.setMemberPostcode(memberEntity.getMemberPostcode());
        memberDTO.setMemberAddress(memberEntity.getMemberAddress());
        memberDTO.setMemberDetailAddress(memberEntity.getMemberDetailAddress());
        memberDTO.setMemberReference(memberEntity.getMemberReference());
        memberDTO.setMemberRole(memberEntity.getMemberRole());
        memberDTO.setMemberCreatedTime(memberEntity.getCreatedTime());
        memberDTO.setMemberUpdatedTime(memberEntity.getUpdatedTime());
        return memberDTO;
    }

}
