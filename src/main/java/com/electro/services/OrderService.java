package com.electro.services;

import com.electro.persistence.Database;
import com.electro.persistence.entities.*;
import com.electro.persistence.repositries.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

public class OrderService {

    private OrderService(){}

    public static void completeOrder(Customer c){
        Database.doInTransactionWithoutResult(em -> {
            double total = 0;
            CustomerRepository customerRepository = new CustomerRepository(em);
            Customer customer = customerRepository.get(c.getId()).get();
            customer.setCreditLimit(BigDecimal.valueOf(customer.getCreditLimit().doubleValue() - total));
            customerRepository.update(customer);
            Order order = new Order();
            order.setCustomer(customer);
            order.setOrderedAt(Instant.now());
            OrderRepository orderRepository = new OrderRepository(em);
            orderRepository.create(order);
            CartRepository cartRepository = new CartRepository(em);
            Cart cart = cartRepository.update(customer.getCart());
            Set<CartItem> cartItems = cart.getCartItems();
            for(CartItem cartItem : cartItems){
                Product product = cartItem.getProduct();
                if(product.getStockQuantity() < cartItem.getQuantity()){
                    throw new RuntimeException("Unfortunately insufficient stock for product: " + product.getProductName());
                }
                total += cartItem.getAmount().doubleValue();
                cartItem.getProduct().setStockQuantity(cartItem.getProduct().getStockQuantity() - cartItem.getQuantity());
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setProduct(product);
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setAmount(cartItem.getAmount());
                CartItemRepository cartItemRepository = new CartItemRepository(em);
                cartItemRepository.delete(cartItem);
                OrderItemRepository orderItemRepository = new OrderItemRepository(em);
                orderItemRepository.create(orderItem);
                order.getOrderItems().add(orderItem);
            }
            double customerCreditLimit = CustomerService.getCreditLimit(c.getId());
            if(customerCreditLimit < total){
                throw new RuntimeException("Insufficient credit limit");
            }
        });
    }
}
