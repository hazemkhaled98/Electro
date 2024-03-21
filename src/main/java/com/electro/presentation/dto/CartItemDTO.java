package com.electro.presentation.dto;

import com.electro.persistence.entities.Cart;
import com.electro.persistence.entities.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;

public class CartItemDTO {

    private Cart cart;

    private Product product;

    private Integer quantity;

    private BigDecimal amount;

}
