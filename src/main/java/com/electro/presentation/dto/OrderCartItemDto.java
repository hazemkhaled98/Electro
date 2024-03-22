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
public class OrderCartItemDto {

    private String name;
    private int quantity;
    private double price;

    public static List<OrderCartItemDto> of(Set<CartItem> cartItems) {
        return cartItems.stream().map(OrderCartItemDto::of).toList();
    }

    public static OrderCartItemDto of(CartItem cartItem) {
        return OrderCartItemDto.builder()
                .name(cartItem.getProduct().getProductName())
                .quantity(cartItem.getQuantity())
                .price(cartItem.getAmount().doubleValue())
                .build();
    }
}
