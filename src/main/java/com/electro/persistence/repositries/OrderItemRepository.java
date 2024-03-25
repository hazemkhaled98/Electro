package com.electro.persistence.repositries;

import com.electro.persistence.entities.OrderItem;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class OrderItemRepository extends Repository<OrderItem>{

    public OrderItemRepository(EntityManager entityManager) {
        super(entityManager);
        super.setType(OrderItem.class);
    }
    public List<Optional<OrderItem>> getOrderItemsByOrderID(Integer orderID){
        List<OrderItem> orderItems = entityManager.createQuery("SELECT oi FROM OrderItem oi WHERE oi.order.id = :orderID", OrderItem.class)
                .setParameter("orderID", orderID)
                .getResultList();
        return orderItems.stream().map(Optional::ofNullable).collect(java.util.stream.Collectors.toList());
    }
}
