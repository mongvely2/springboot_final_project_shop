package com.example.shop.service;

import com.example.shop.dto.CartDTO;
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

            CartEntity cart = cartRepository.findByMemberEntity(memberEntity).get();
            ProductEntity productEntity = productRepository.findById(productId).get();
            CartProductEntity cartProductEntity = CartProductEntity.saveCartProduct(productEntity, cart, cartCount);
            cartProductRepository.save(cartProductEntity);
            return 1;

        } else {    // 장바구니 중복상품 체크
//            카트가 존재하기에 기존 정보에서 entity 정보 불러와야 함
            CartEntity cart = cartEntity.get();

            ProductEntity productEntity = productRepository.findById(productId).get();
            List<CartProductEntity> cartProductEntityList = cartProductRepository.findAllByCartEntityAndProductEntity(cart, productEntity);

            if (cartProductEntityList.isEmpty()) {      // 중복이 아닌 경우
                CartProductEntity cartProductEntity = CartProductEntity.saveCartProduct(productEntity, cart, cartCount);
                cartProductRepository.save(cartProductEntity);
                return 1;
            } else {
//                중복인 경우
                return 2;
            }

        }

    }

//    public List<CartItemDTO> findAll(String userId) {
//        MemberEntity memberEntity = memberRepository.findByUserId(userId).get();
//        Optional<CartEntity>cartEntity = cartRepository.findByMemberEntity(memberEntity);
//        if (cartEntity.isPresent()) {
//            CartEntity cartEntity1 = cartEntity.get();
//            List<CartItemEntity>cartItemEntityList = cartItemRepository.findByCartEntity(cartEntity1);
//            List<CartItemDTO>cartItemDTOList = new ArrayList<>();
//            for (CartItemEntity cartItemEntity : cartItemEntityList) {
//                CartItemDTO cartItemDTO = new CartItemDTO();
//                cartItemDTO.setId(cartItemEntity.getId());
//                cartItemDTO.setCartCount(cartItemEntity.getCartCount());
//                cartItemDTO.setItemName(cartItemEntity.getItemEntity().getItemName());
//                cartItemDTO.setItemPrice(cartItemEntity.getItemEntity().getItemPrice());
//                cartItemDTO.setItemImage(cartItemEntity.getItemEntity().getItemFileEntityList().get(0).getStoredFileNameItem());
//                cartItemDTOList.add(cartItemDTO);
//            }
//            return cartItemDTOList;
//
//        }else {
//            return null;
//        }

    @Transactional
    public List<CartProductDTO> findAll(Long memberId) {
        MemberEntity memberEntity = memberRepository.findById(memberId).get();
        CartEntity cartEntity = cartRepository.findByMemberEntity(memberEntity).get();
        List<CartProductEntity> cartProductEntityList =
                cartProductRepository.findAllByCartEntityOrderByIdDesc(cartEntity);

        List<CartProductDTO> cartProductDTOList = new ArrayList<>();
        for (CartProductEntity var : cartProductEntityList) {
            cartProductDTOList.add(CartProductDTO.toCartProductDTO(var));
        }
        return cartProductDTOList;
    }
}



