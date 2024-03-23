package com.electro.persistence.util;

import com.electro.persistence.entities.Cart;
import com.electro.persistence.entities.CartItem;
import com.electro.persistence.entities.Customer;
import com.electro.persistence.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PopulateCart {
    public static void main(String[] args) {
        // Populate the database with carts
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("electro");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Cart cart = entityManager.find(Cart.class, 2);
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(entityManager.find(Product.class, 7));
        cartItem.setQuantity(2);
        cartItem.setAmount(cartItem.getProduct().getProductPrice().multiply(java.math.BigDecimal.valueOf(cartItem.getQuantity())));
        entityManager.persist(cartItem);
        CartItem cartItem2 = new CartItem();
        cartItem2.setCart(cart);
        cartItem2.setProduct(entityManager.find(Product.class, 8));
        cartItem2.setQuantity(1);
        cartItem2.setAmount(cartItem2.getProduct().getProductPrice().multiply(java.math.BigDecimal.valueOf(cartItem2.getQuantity())));
        entityManager.persist(cartItem2);
        CartItem cartItem3 = new CartItem();
        cartItem3.setCart(cart);
        cartItem3.setProduct(entityManager.find(Product.class, 9));
        cartItem3.setQuantity(3);
        cartItem3.setAmount(cartItem3.getProduct().getProductPrice().multiply(java.math.BigDecimal.valueOf(cartItem3.getQuantity())));
        entityManager.persist(cartItem3);
        entityManager.getTransaction().commit();
    }
}
