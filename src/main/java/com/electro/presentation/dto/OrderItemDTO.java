package com.electro.presentation.dto;

import com.electro.persistence.entities.Order;
import com.electro.persistence.entities.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;

public class OrderItemDTO {

    private Integer id;


    private Order order;

    private Product product;

    private Integer quantity;

    private BigDecimal amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
