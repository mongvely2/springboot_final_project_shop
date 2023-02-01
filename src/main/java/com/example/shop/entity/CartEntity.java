package com.example.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "cart_table")
public class CartEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "cartEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CartProductEntity> cartProductEntityList = new ArrayList<>();

    public static CartEntity saveCartEntity(MemberEntity memberEntity) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setMemberEntity(memberEntity);
        return cartEntity;
    }
}
