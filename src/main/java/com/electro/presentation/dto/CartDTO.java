package com.electro.presentation.dto;

import com.electro.persistence.entities.CartItem;
import com.electro.persistence.entities.Customer;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

public class CartDTO {



    private Customer customer;


    private Set<CartItem> cartItems = new LinkedHashSet<>();

}
