package com.electro.presentation.dto;


import com.electro.persistence.entities.CartItem;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Builder
@Getter
@ToString
public class CartItemDto {

    private String name;
    private int quantity;
    private double price;

    public static List<CartItemDto> of(Set<CartItem> cartItems) {
        return cartItems.stream().map(CartItemDto::of).toList();
    }

    public static CartItemDto of(CartItem cartItem) {
        return CartItemDto.builder()
                .name(cartItem.getProduct().getProductName())
                .quantity(cartItem.getQuantity())
                .price(cartItem.getAmount().doubleValue())
                .build();
    }
}
