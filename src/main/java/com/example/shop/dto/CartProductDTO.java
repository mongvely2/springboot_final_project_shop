package com.example.shop.dto;

import com.example.shop.entity.CartEntity;
import com.example.shop.entity.CartProductEntity;
import com.example.shop.entity.ProductEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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

    public static CartProductDTO toCartProductDTO(CartProductEntity cartProductEntity) {
        CartProductDTO cartProductDTO = new CartProductDTO();
        cartProductDTO.setId(cartProductEntity.getId());
        cartProductDTO.setCartCount(cartProductEntity.getCartCount());
        cartProductDTO.setCartId(cartProductEntity.getCartEntity().getId());
        cartProductDTO.setProductId(cartProductEntity.getProductEntity().getId());
        return cartProductDTO;
    }


}
