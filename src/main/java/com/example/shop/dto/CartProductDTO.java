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

    private Long cartId;
    private Long productId;

//    productEntity db 정보
    private String thumbFileName;
    private String productName;
    private String productPrice;
    private String productDelively;




    public static CartProductDTO toCartProductDTO(CartProductEntity cartProductEntity) {
        CartProductDTO cartProductDTO = new CartProductDTO();
        cartProductDTO.setId(cartProductEntity.getId());
        cartProductDTO.setCartCount(cartProductEntity.getCartCount());
        cartProductDTO.setCartId(cartProductEntity.getCartEntity().getId());
        cartProductDTO.setProductId(cartProductEntity.getProductEntity().getId());
        cartProductDTO.setThumbFileName(cartProductEntity.getProductEntity().getFileEntityList().get(0).getStoredFileName());
        cartProductDTO.setProductName(cartProductEntity.getProductEntity().getProductName());
        cartProductDTO.setProductPrice(cartProductEntity.getProductEntity().getProductPrice());
        cartProductDTO.setProductDelively(cartProductEntity.getProductEntity().getProductDelively());
        return cartProductDTO;
    }


}
