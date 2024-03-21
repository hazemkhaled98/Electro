package com.electro.services;

import com.electro.persistence.Database;
import com.electro.persistence.entities.Customer;
import com.electro.persistence.entities.Product;
import com.electro.persistence.repositries.ProductRepository;
import com.electro.presentation.dto.CartItemDTO;
import com.electro.presentation.dto.CartItemProductDTO;
import com.electro.presentation.enums.SessionAttribute;
import jakarta.servlet.http.HttpSession;

import java.util.*;

public class CartService {
    public static Boolean addToTemporaryCart(HttpSession session, String productName, int quantity) {

        List<CartItemDTO> cartItems = (List<CartItemDTO>) session.getAttribute(SessionAttribute.CART_ITEMS.name());
        if (cartItems == null) {
            cartItems = new ArrayList<>();
            session.setAttribute(SessionAttribute.CART_ITEMS.name(), cartItems);
        }

        Optional<Product> product =Database.doInTransaction(em -> {
            ProductRepository productRepository = new ProductRepository(em);
            return productRepository.getByName(productName);
        });
        if (product.isPresent()) {
            CartItemProductDTO itemProductDTO = mapProductToCartItemProductDTO(product.get());
            CartItemDTO cartItem=CartItemDTO.builder()
                    .itemProductDTO(itemProductDTO)
                    .quantity(quantity)
                    .build();
            cartItems.add(cartItem);
            return true;
        }
        return false;
    }

    public static boolean addToPermanentCart(Customer customer, String productName, int quantity) {
        Optional<Product> product =Database.doInTransaction(em -> {
            ProductRepository productRepository = new ProductRepository(em);
            return productRepository.getByName(productName);
        });
        if (product.isPresent()) {

            return true;
        }
        return false;
    }

    private static CartItemProductDTO mapProductToCartItemProductDTO(Product product) {

        return CartItemProductDTO.builder()
                .id(product.getId())
                .productDescription(product.getProductDescription())
                .productPic(product.getProductPic())
                .productPrice(product.getProductPrice())
                .stockQuantity(product.getStockQuantity())
                .productName(product.getProductName())
                .category(product.getCategory())
                .build();
    }
}
