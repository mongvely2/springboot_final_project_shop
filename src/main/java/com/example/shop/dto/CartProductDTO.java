package com.example.shop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CartProductDTO {
    private Long id;
    private int cartCount;

//    private Long memberId;
    private Long cartId;
    private Long productId;

//    public static CartProductDTO cartDTO(CartEntity cartEntity, MemberEntity memberEntity, List<CartProductEntity> productEntityList) {
//        CartProductDTO cartDTO = new CartProductDTO();
//        cartDTO.setId(cartEntity.getId());
//        cartDTO.setCartCount(cartEntity.getCartCount());
//        cartDTO.setMemberId(memberEntity.getId());
//        for (CartProductEntity var : productEntityList) {
//            cartDTO.setProductId(var.getId());
//        }
//        return cartDTO;
//    }



}
