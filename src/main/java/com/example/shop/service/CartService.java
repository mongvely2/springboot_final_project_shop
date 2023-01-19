package com.example.shop.service;

import com.example.shop.dto.CartDTO;
import com.example.shop.entity.CartEntity;
import com.example.shop.entity.MemberEntity;
import com.example.shop.entity.ProductEntity;
import com.example.shop.repository.CartRepository;
import com.example.shop.repository.MemberRepository;
import com.example.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    //    장바구니 추가
    public int addCart(CartDTO cartDTO) {
//        이미 추가된 장바구니 중복여부 확인(중복일 경우 2반환)
        System.out.println("cartDTO service = " + cartDTO);
//        쿼리 커스텀으로 진행
        Long memberId = cartDTO.getMemberId();
        Long productId = cartDTO.getProductId();
        CartEntity cart = cartRepository.findByCart(memberId, productId);
        System.out.println("cart = " + cart);

        if (cart != null) {
//        if (cart) {
            return 2;
        } else {
//        중복여부 아닐 경우 장바구니 DB 저장 진행
            MemberEntity memberEntity = memberRepository.findById(cartDTO.getMemberId()).get();
            ProductEntity productEntity = productRepository.findById(cartDTO.getProductId()).get();
            CartEntity cartEntity = CartEntity.toSaveCartEntity(cartDTO, memberEntity, productEntity);
            cartRepository.save(cartEntity);
            return 1;
        }
    }
}
