package com.example.shop.repository;

import com.example.shop.dto.CartDTO;
import com.example.shop.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
//    @Query(value = "select * from cart_table where memberId= and productId ", nativeQuery = true)
//    CartEntity findByCart(CartDTO cartDTO);

    @Query(value = "select * from cart_table where member_id=${memberId} and product_id=${productId}", nativeQuery = true)
    CartEntity findByCart(Long memberId, Long productId);
//    CartEntity findByCart(@Param("member") Long memberId, @Param("product") Long productId);
}
