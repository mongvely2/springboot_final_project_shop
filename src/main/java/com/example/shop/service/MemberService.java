package com.example.shop.service;

import com.example.shop.dto.MemberDTO;
import com.example.shop.entity.MemberEntity;
import com.example.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public MemberDTO login(MemberDTO memberDTO) throws Exception {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if (optionalMemberEntity.isPresent()) {
            MemberEntity memberEntity = optionalMemberEntity.get();
            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                MemberDTO result = MemberDTO.toDTO(memberEntity);
                return result;
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    public Page<MemberDTO> memberListPaging(Pageable pageable) {
//        page: (하단에 표시되는) 해당 page(배열처럼 0번이 1번임) / pageLimit: 보여줄 한 페이지에서의 게시글 수
        int page = pageable.getPageNumber()-1;
        final int pageLimit = 10;
//        Page<> : 스프링에서 제공하는 인터페이스 / List<> 랑 헷갈리면 안 됨
        Page<MemberEntity> memberEntities =
        memberRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC,"id")));
        Page<MemberDTO> memberList = memberEntities.map(
//                boardEntities에 담긴 boardEntity 객체를 board에 담아서
//                boardDTO 객체로 하나씩 옮겨 담는 과정
//                MemberDTO 클래스에서 this. 으로 담아줘야함
                member -> new MemberDTO(
                        member.getId(),
                        member.getMemberEmail(),
                        member.getMemberPassword(),
                        member.getMemberName(),
                        member.getMemberMobile(),
                        member.getMemberPostcode(),
                        member.getMemberAddress(),
                        member.getMemberDetailAddress(),
                        member.getMemberReference(),
                        member.getMemberRole(),
                        member.getCreatedTime(),
                        member.getUpdatedTime()
                )
        );
        return memberList;

    }
}
