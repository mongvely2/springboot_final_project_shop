package com.example.shop.entity;

import com.example.shop.dto.CartDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "cart_table")
public class CartEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 5)
    private int cartCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    public static CartEntity toSaveCartEntity(CartDTO cartDTO, MemberEntity memberEntity, ProductEntity productEntity) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setCartCount(cartDTO.getCartCount());
        cartEntity.setMemberEntity(memberEntity);
        cartEntity.setProductEntity(productEntity);
        return cartEntity;
    }
}
