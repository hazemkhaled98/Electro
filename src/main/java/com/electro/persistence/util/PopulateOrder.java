package com.electro.persistence.util;

import com.electro.persistence.entities.Customer;
import com.electro.persistence.entities.Order;
import com.electro.persistence.entities.OrderItem;
import com.electro.persistence.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.time.Instant;

public class PopulateOrder {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("electro");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Customer customer = new Customer();
        customer = entityManager.find(Customer.class, 1);
        Order order = new Order();
        order.setOrderedAt(Instant.now());
        order.setCustomer(customer);
        OrderItem orderItem = new OrderItem();
        order.setOrderItems(null);
        entityManager.persist(order);
        orderItem.setOrder(order);
        orderItem.setAmount(BigDecimal.valueOf(2));
        Product product = entityManager.find(Product.class, 1);
        orderItem.setProduct(product);
        orderItem.setQuantity(2);
entityManager.persist(orderItem);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
