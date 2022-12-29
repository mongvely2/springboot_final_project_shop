package com.example.shop.service;

import com.example.shop.dto.MemberDTO;
import com.example.shop.entity.MemberEntity;
import com.example.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public String duplicateCheck(String inputEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(inputEmail);
        if (optionalMemberEntity.isEmpty()) {
            return "Y";
        } else {
            return null;
        }

    }


    public Long save(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toSaveEntity(memberDTO);
        Long savedId = memberRepository.save(memberEntity).getId();
        return savedId;
    }
}
