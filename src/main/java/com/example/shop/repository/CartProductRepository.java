package com.example.shop.repository;

import com.example.shop.entity.CartEntity;
import com.example.shop.entity.CartProductEntity;
import com.example.shop.entity.MemberEntity;
import com.example.shop.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartProductRepository extends JpaRepository<CartProductEntity, Long> {
    List<CartProductEntity> findAllByCartEntityAndProductEntity(CartEntity cart, ProductEntity productEntity);

    List<CartProductEntity> findAllByCartEntityOrderByIdDesc(CartEntity cartEntity);


//    @Query(value = "select c from CartProductEntity c where c.memberEntity.id = :member and c.productEntity.id = :product")
//    CartProductEntity findByCart(@Param("member") Long memberId,
//                                 @Param("product") Long productId);
//
//    List<CartProductEntity> findAllByMemberEntityOrderByIdDesc(MemberEntity memberEntity);
}
