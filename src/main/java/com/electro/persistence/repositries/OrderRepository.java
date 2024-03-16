package com.electro.persistence.repositries;

import com.electro.persistence.entities.Customer;
import com.electro.persistence.entities.Order;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderRepository extends Repository<Order> {
    public OrderRepository(EntityManager entityManager) {
        super(entityManager);
        super.setType(Order.class);
    }
    public List<Optional<Order>> getOrderByCustomerID(Integer customerID){
        List<Order> orders = entityManager.createQuery("SELECT o FROM Order o WHERE o.customer.id = :customerID", Order.class)
                .setParameter("customerID", customerID)
                .getResultList();
        return orders.stream().map(Optional::ofNullable).collect(Collectors.toList());
    }

}
