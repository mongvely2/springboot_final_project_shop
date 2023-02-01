package com.example.shop.repository;

import com.example.shop.entity.CartEntity;
import com.example.shop.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
//    CartEntity findByMemberEntity(MemberEntity memberEntity);
    Optional<CartEntity> findByMemberEntity(MemberEntity memberEntity);

//    CartEntity findByMemberId(Long memberId);
}
