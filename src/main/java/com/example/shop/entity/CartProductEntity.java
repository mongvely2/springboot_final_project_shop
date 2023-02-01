package com.example.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "cart_product_table")
public class CartProductEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 5)
    private int cartCount;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private CartEntity cartEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    public static CartProductEntity saveCartProduct(ProductEntity productEntity, CartEntity cartEntity, int cartCount) {
        CartProductEntity cartProductEntity = new CartProductEntity();
        cartProductEntity.setCartCount(cartCount);
        cartProductEntity.setCartEntity(cartEntity);
        cartProductEntity.setProductEntity(productEntity);
        return cartProductEntity;
    }



//    public static CartEntity toSaveCartEntity(CartDTO cartDTO, MemberEntity memberEntity, CartProductEntity productEntity) {
//        CartEntity cartEntity = new CartEntity();
//        cartEntity.setCartCount(cartDTO.getCartCount());
//        cartEntity.setMemberEntity(memberEntity);
//        cartEntity.setProductEntity(productEntity);
//        return cartEntity;
//    }
}
