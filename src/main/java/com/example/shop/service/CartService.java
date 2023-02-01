package com.example.shop.service;

import com.example.shop.dto.CartProductDTO;
import com.example.shop.entity.CartEntity;
import com.example.shop.entity.CartProductEntity;
import com.example.shop.entity.MemberEntity;
import com.example.shop.entity.ProductEntity;
import com.example.shop.repository.CartProductRepository;
import com.example.shop.repository.CartRepository;
import com.example.shop.repository.MemberRepository;
import com.example.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartProductRepository cartProductRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    @Transactional
    public int addCart(Long productId, int cartCount, Long memberId) {
        System.out.println("productId = " + productId + ", cartCount = " + cartCount + ", memberId = " + memberId);
        MemberEntity memberEntity = memberRepository.findById(memberId).get();
        Optional<CartEntity> cartEntity = cartRepository.findByMemberEntity(memberEntity);

        if (cartEntity.isEmpty()) {     // 첫 장바구니(장바구니 없을 시 생성)
            CartEntity cartEntity1 = CartEntity.saveCartEntity(memberEntity);
            cartRepository.save(cartEntity1);

//            CartEntity cart = cartEntity.get();
            CartEntity cart = cartRepository.findByMemberEntity(memberEntity).get();
//            CartEntity cart = cartRepository.findByMemberEntity(memberEntity).get();
//            CartEntity cart = cartRepository.findByMemberId(memberId);
//            CartEntity cart = cartRepository.findByMemberEntity(memberEntity).get();
            ProductEntity productEntity = productRepository.findById(productId).get();
            CartProductEntity cartProductEntity = CartProductEntity.saveCartProduct(productEntity, cart, cartCount);
            cartProductRepository.save(cartProductEntity);
            return 1;

//        } else if (cartEntity.isPresent()) {    // 장바구니 중복상품 체크
        } else {    // 장바구니 중복상품 체크
            CartEntity cart = cartEntity.get();
//            CartEntity cart = cartRepository.findByMemberEntity(memberEntity).get();

            ProductEntity productEntity = productRepository.findById(productId).get();
            List<CartProductEntity> cartProductEntityList = cartProductRepository.findAllByCartEntityAndProductEntity(cart, productEntity);

            if (cartProductEntityList.isEmpty()) {      // 중복이 아닌 경우
//                CartEntity cart = cartRepository.findByMemberId(memberId);
//                ProductEntity productEntity = productRepository.findById(productId).get();
                CartProductEntity cartProductEntity = CartProductEntity.saveCartProduct(productEntity, cart, cartCount);
                cartProductRepository.save(cartProductEntity);
                return 1;
            } else {
//                중복인 경우
                return 2;
            }

        }

    }


//    ------------------------------------------------------

    //    장바구니 추가
//    public int addCart(CartProductDTO cartDTO) {
//        System.out.println("cartDTO service = " + cartDTO);
////        쿼리 커스텀으로 진행
//        Long memberId = cartDTO.getMemberId();
//        Long productId = cartDTO.getProductId();
//        CartProductEntity cart = cartRepository.findByCart(memberId, productId);
//        System.out.println("cart = " + cart);
//
//        if (cart != null) {
////        이미 추가된 장바구니 중복여부 확인(중복일 경우 2반환)
//            return 2;
//        } else {
////        중복여부 아닐 경우 장바구니 DB 저장 후 1반환
//            MemberEntity memberEntity = memberRepository.findById(memberId).get();
//            ProductEntity productEntity = productRepository.findById(productId).get();
//            CartProductEntity cartEntity = CartProductEntity.toSaveCartEntity(cartDTO, memberEntity, productEntity);
//            cartRepository.save(cartEntity);
//            return 1;
//        }
//    }
//
//    @Transactional
//    public List<CartProductDTO> findAll(Long memberId) {
////        memberId로 meberEntity 불러오기
//        MemberEntity memberEntity = memberRepository.findById(memberId).get();
//        List<ProductEntity> productEntityList = productRepository.findAll();
////        가져온 memberEntity 값을 넣어 해당되는 memberId의 "cartEntity" 목록 불러오기
//        List<CartProductEntity> cartEntityList =
//                cartRepository.findAllByMemberEntityOrderByIdDesc(memberEntity);
////        cartEntity 를 DTO 로  반복문으로 넣어주기 -> controller 넘기기 위해
//        List<CartProductDTO> cartDTOList = new ArrayList<>();
//        for (CartProductEntity var : cartEntityList) {
//            cartDTOList.add(CartProductDTO.cartDTO(var, memberEntity, productEntityList));
//        }
//        return cartDTOList;
//
//    }
}
