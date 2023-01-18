package com.example.shop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CartDTO {
    private Long id;
    private int cartCount;

    private Long memberId;
    private Long productId;
}
