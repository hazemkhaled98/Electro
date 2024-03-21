package com.electro.persistence.repositries;

import com.electro.persistence.entities.CartItem;
import jakarta.persistence.EntityManager;

public class CartItemRepository extends Repository<CartItem> {


    public CartItemRepository(EntityManager entityManager) {
        super(entityManager);
        setType(CartItem.class);
    }
}
