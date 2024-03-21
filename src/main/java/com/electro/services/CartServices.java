package com.electro.services;

import com.electro.persistence.entities.Cart;
import com.electro.persistence.entities.CartItem;
import com.electro.persistence.entities.Customer;
import com.electro.persistence.entities.Product;
import com.electro.presentation.dto.CartItemDTO;
import com.electro.services.util.ImagesPathUtil;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CartServices {
    public List<String> getCartItems(Customer customer) throws IOException {
        Cart cart = customer.getCart();
        Set<CartItem> cartItems = cart.getCartItems();
        List<String> cartItemsJson = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            cartItemsJson.add(productToJson(cartItem.getProduct(), cartItem.getQuantity()));
        }
        return cartItemsJson;
    }
    private String productToJson(Product product , int quantity) throws IOException {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("name", product.getProductName())
                .add("price", product.getProductPrice())
                .add("quantity", quantity)
                .add("mimeType" , ImagesPathUtil.getMimeType(product.getProductPic()))
                .add("image", ImagesPathUtil.encodeFileToBase64(product.getProductPic()));
        return jsonObjectBuilder.build().toString();
    }
}
