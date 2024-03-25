package com.electro.services;

import com.electro.persistence.Database;
import com.electro.persistence.entities.*;
import com.electro.persistence.repositries.*;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

public class OrderService {

    private OrderService(){}

    public static void completeOrder(Customer c){
        Database.doInTransactionWithoutResult(em -> {
            Customer customer = getCustomer(em, c.getId());
            Order order = createOrder(em, customer);
            Cart cart = getCart(em, customer);
            double total = processCartItems(em, cart, order);
            updateCreditLimit(em, customer, total);
        });
    }

    private static Customer getCustomer(EntityManager em, int id){
        CustomerRepository customerRepository = new CustomerRepository(em);
        return customerRepository.get(id).get();
    }

    private static Order createOrder(EntityManager em,  Customer customer){
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderedAt(Instant.now());
        OrderRepository orderRepository = new OrderRepository(em);
        orderRepository.create(order);
        return order;
    }

    private static Cart getCart(EntityManager em, Customer customer){
        CartRepository cartRepository = new CartRepository(em);
        return cartRepository.update(customer.getCart());
    }

    private static double processCartItems(EntityManager em,  Cart cart,  Order order){
        double total = 0;
        Set<CartItem> cartItems = cart.getCartItems();
        for(CartItem cartItem : cartItems){
            Product product = cartItem.getProduct();
            if(product.getStockQuantity() < cartItem.getQuantity()){
                throw new RuntimeException("Unfortunately, insufficient stock for product: " + product.getProductName());
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
        return total;
    }

    private static void updateCreditLimit(EntityManager em, Customer customer, double total) {
        double customerCreditLimit = customer.getCreditLimit().doubleValue();
        if(customerCreditLimit < total){
            throw new RuntimeException("Insufficient credit limit");
        }
        customer.setCreditLimit(BigDecimal.valueOf(customer.getCreditLimit().doubleValue() - total));
        CustomerRepository customerRepository = new CustomerRepository(em);
        customerRepository.update(customer);
    }
}
