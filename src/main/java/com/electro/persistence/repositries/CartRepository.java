package com.electro.persistence.repositries;

import com.electro.persistence.entities.Cart;
import jakarta.persistence.EntityManager;

public class CartRepository extends Repository<Cart>{


    public CartRepository(EntityManager entityManager) {
        super(entityManager);
        super.setType(Cart.class);
    }
}
