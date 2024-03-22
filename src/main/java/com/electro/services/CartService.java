package com.electro.services;

import com.electro.persistence.Database;
import com.electro.persistence.entities.Cart;
import com.electro.persistence.entities.CartItem;
import com.electro.persistence.entities.Customer;
import com.electro.persistence.repositries.CartRepository;
import com.electro.presentation.dto.OrderCartItemDto;

import java.util.List;
import java.util.Set;

public class CartService {

    private CartService() {
    }

    public static List<OrderCartItemDto> getCartItems(Customer customer){
        return Database.doInTransaction(em -> {
            CartRepository cartRepository = new CartRepository(em);
            Cart cart = cartRepository.update(customer.getCart());
            Set<CartItem> cartItems = cart.getCartItems();
            return OrderCartItemDto.of(cartItems);
        });
    }

    public static List<OrderCartItemDto> getCartItems(Cart c){
        return Database.doInTransaction(em -> {
            CartRepository cartRepository = new CartRepository(em);
            Cart cart = cartRepository.update(c);
            Set<CartItem> cartItems = cart.getCartItems();
            return OrderCartItemDto.of(cartItems);
        });
    }

    public static double getTotalPrice(List<OrderCartItemDto> orderCartItemDto){
        return orderCartItemDto.stream().mapToDouble(OrderCartItemDto::getPrice).sum();
    }
}
