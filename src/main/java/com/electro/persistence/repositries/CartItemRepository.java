package com.electro.persistence.repositries;

import com.electro.persistence.entities.Cart;
import com.electro.persistence.entities.CartItem;
import com.electro.persistence.entities.Customer;
import com.electro.persistence.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class CartItemRepository extends Repository<CartItem>{
    public CartItemRepository(EntityManager entityManager) {
        super(entityManager);
        super.setType(CartItem.class);
    }
    public Optional<CartItem> getExistingCartItemForCustomer(Product product, Customer customer) {
        if (product == null || customer == null || customer.getCart() == null) {
            return Optional.empty();
        }

        TypedQuery<CartItem> query = entityManager.createQuery(
                "SELECT c FROM CartItem c WHERE c.cart = :customerCart AND c.product = :product",
                CartItem.class
        );

        query.setParameter("customerCart", customer.getCart());
        query.setParameter("product", product);

        try {
            CartItem cartItem = query.getSingleResult();
            return Optional.of(cartItem);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }


    public Long getTotalQuantityOfProduct(Product product) {
        Query query = entityManager.createQuery("SELECT SUM(ci.quantity) FROM CartItem ci WHERE ci.product = :product");
        query.setParameter("product", product);
        Long totalQuantity = (Long) query.getSingleResult();
        return totalQuantity != null ? totalQuantity : 0;
    }

}
