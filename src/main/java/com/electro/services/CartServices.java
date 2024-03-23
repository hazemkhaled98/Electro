/*
package com.electro.services;

import com.electro.persistence.Database;
import com.electro.persistence.entities.Cart;
import com.electro.persistence.entities.CartItem;
import com.electro.persistence.entities.Customer;
import com.electro.persistence.entities.Product;
import com.electro.persistence.repositries.CartItemRepository;
import com.electro.persistence.repositries.CartRepository;
import com.electro.presentation.dto.CartItemDTO;
import com.electro.presentation.dto.CartItemProductDTO;
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
        int i = 0;
        for (CartItem cartItem : cartItems) {
            cartItemsJson.add(productToJson(cartItem.getProduct(), cartItem.getQuantity(),i, cartItem.getId()));
            i++;
        }
        return cartItemsJson;
    }
//new code after moataz
    public List<CartItemDTO> newgetCartItems(Customer customer) {
        Cart cart = customer.getCart();
        Set<CartItem> cartItems = cart.getCartItems();
        List<CartItemDTO> cartItemsDTO = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            CartItemDTO cartItemDTO = CartItemDTO.builder().build();
            cartItemDTO.setCartItemId(cartItem.getId());
            cartItemDTO.setQuantity(cartItem.getQuantity());
            CartItemProductDTO itemProductDTO = mapProductToCartItemProductDTO(cartItem.getProduct());
            cartItemDTO.setItemProductDTO(itemProductDTO);
            cartItemsDTO.add(cartItemDTO);
        }
        return cartItemsDTO;
    }

    private String productToJson(Product product , int quantity , int order,int id) throws IOException {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("name", product.getProductName())
                .add("id", id)
                .add("price", product.getProductPrice())
                .add("quantity", quantity)
                .add("mimeType" , ImagesPathUtil.getMimeType(product.getProductPic()))
                .add("image", ImagesPathUtil.encodeFileToBase64(product.getProductPic()))
                .add("order", order);
        return jsonObjectBuilder.build().toString();
    }
    public void removeItemFromCart(int id){
        Database.doInTransaction(em -> {
            CartItemRepository cartItemRepository = new CartItemRepository(em);
            cartItemRepository.deleteById(id);
            return null;
        });
    }
// new  code after moataz
    public CartItemProductDTO mapProductToCartItemProductDTO(Product product) {
        CartItemProductDTO itemProductDTO = CartItemProductDTO.builder().build();
        itemProductDTO.setId(product.getId());
        itemProductDTO.setProductName(product.getProductName());
        itemProductDTO.setStockQuantity(product.getStockQuantity());
        itemProductDTO.setProductDescription(product.getProductDescription());
        itemProductDTO.setProductPic(product.getProductPic());
        itemProductDTO.setProductPrice(product.getProductPrice());
        itemProductDTO.setCategory(product.getCategory());
        return itemProductDTO;
    }

}
*/
