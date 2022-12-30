package com.example.shop.entity;

import com.example.shop.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "member_table")
public class MemberEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String memberEmail;

    @Column(length = 20)
    private String memberPassword;

    @Column(length = 30)
    private String memberName;

    @Column(length = 15)
    private String memberMobile;

    @Column(length = 10)
    private String memberPostcode;

    @Column(length = 100)
    private String memberAddress;

    @Column(length = 50)
    private String memberDetailAddress;

    @Column(length = 50)
    private String memberReference;

    @Column
    private int memberRole;

    public static MemberEntity toSaveEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberMobile(memberDTO.getMemberMobile());
        memberEntity.setMemberPostcode(memberDTO.getMemberPostcode());
        memberEntity.setMemberAddress(memberDTO.getMemberAddress());
        memberEntity.setMemberDetailAddress(memberDTO.getMemberDetailAddress());
        memberEntity.setMemberReference(memberDTO.getMemberReference());
        if (memberDTO.getMemberEmail().equals("admin")) {
            memberEntity.setMemberRole(0);
        } else {
            memberEntity.setMemberRole(1);
        }
        return memberEntity;
    }

}

























