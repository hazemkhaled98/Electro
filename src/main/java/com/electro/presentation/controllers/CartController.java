package com.electro.presentation.controllers;

import com.electro.persistence.entities.Cart;
import com.electro.persistence.entities.CartItem;
import com.electro.persistence.entities.Customer;
import com.electro.persistence.entities.Product;
import com.electro.presentation.dto.CartItemDTO;
import com.electro.presentation.enums.SessionAttribute;
import com.electro.services.CartService;
//import com.electro.services.CartServices;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebServlet(name = "CartController", urlPatterns = "/cart")
public class CartController  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        PrintWriter out = resp.getWriter();

        if (session == null) {
            out.print(new ArrayList<>());
        }
        else {
                List<CartItemDTO> cartItemSessionDTO = new ArrayList<>();
                cartItemSessionDTO = (List<CartItemDTO>) req.getSession(false).getAttribute(SessionAttribute.CART_ITEMS.toString());
            if (cartItemSessionDTO != null){
                List<String> cartItemSession = new ArrayList<>();
                for (CartItemDTO cartItemDTO : cartItemSessionDTO) {
                    JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
                    jsonObjectBuilder.add("name", cartItemDTO.getItemProductDTO().getProductName())
                            .add("id", cartItemDTO.getCartItemId())
                            .add("price", cartItemDTO.getItemProductDTO().getProductPrice())
                            .add("quantity", cartItemDTO.getQuantity())
                            .add("image", cartItemDTO.getItemProductDTO().getProductPic())
                            .add("order", cartItemDTO.getCartItemId())
                            .add("amount",cartItemDTO.getAmount());
                    cartItemSession.add(jsonObjectBuilder.build().toString());
                }
                out.print(cartItemSession);
                out.flush();
            }
            else{
                // original
                Customer customer = (Customer) req.getSession(false).getAttribute(SessionAttribute.LOGGED_IN_CUSTOMER.toString());
                List<String> cartItemsJson = new ArrayList<>();
                CartService cartServices = new CartService();
                /*cartItemsJson = cartServices.getCartItems(customer);
                req.getSession(true).setAttribute(SessionAttribute.CART_PRODUCTS.toString(),cartItemsJson);
                out.print(cartItemsJson);
                out.flush();*/
                //test
                List<CartItemDTO> cartItemsDTO = new ArrayList<>();
                cartItemsDTO = cartServices.newgetCartItems(customer);
                for (CartItemDTO cartItemDTO : cartItemsDTO) {
                    JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
                    jsonObjectBuilder.add("name", cartItemDTO.getItemProductDTO().getProductName())
                            .add("id", cartItemDTO.getCartItemId())
                            .add("price", cartItemDTO.getItemProductDTO().getProductPrice())
                            .add("quantity", cartItemDTO.getQuantity())
                            .add("image", cartItemDTO.getItemProductDTO().getProductPic())
                            .add("order", cartItemDTO.getCartItemId())
                            .add("amount",cartItemDTO.getAmount());
                    cartItemsJson.add(jsonObjectBuilder.build().toString());
                }
                req.getSession(true).setAttribute(SessionAttribute.CART_ITEMS.toString(),cartItemsDTO);
                out.print(cartItemsJson);
                out.flush();

            }

        }

            /*Cart cart = customer.getCart();
            Set<CartItem> cartItems = cart.getCartItems();
            List<String> cartItemsJson = new ArrayList<>();
            try{
                for (CartItem cartItem : cartItems) {
                    cartItemsJson.add(productToJson(cartItem.getProduct(), cartItem.getQuantity()));
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

            System.out.println("CART ITEMS JSON "+cartItemsJson);
            System.out.println("CART ITEMS "+cartItems);*/

        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = 0;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        Customer cust = (Customer) req.getSession(false).getAttribute(SessionAttribute.LOGGED_IN_CUSTOMER.toString());
        if (cust != null){
            CartService cartServices = new CartService();
            cartServices.removeItemFromCart(id);
        }
        List<CartItemDTO> cartItemsSessionDTO = new ArrayList<>();
        cartItemsSessionDTO = (List<CartItemDTO>) req.getSession(false).getAttribute(SessionAttribute.CART_ITEMS.toString());
        System.out.println("Old size "+cartItemsSessionDTO.size());
        for (int i = 0; i < cartItemsSessionDTO.size(); i++) {
            //if (cartItemsSessionDTO.get(i).contains("\"id\":" + id)) {
            if (cartItemsSessionDTO.get(i).getCartItemId() == id) {
                cartItemsSessionDTO.remove(i);
                break;
            }
        }
        System.out.println("CART ITEMS SESSION " + cartItemsSessionDTO.size());
    }

    /*private String productToJson(Product product , int quantity) throws IOException {
        System.out.println("PRODUCT TO JSON");
        // Convert a product to JSON
        // This is just a placeholder, replace it with your actual code to convert a product to JSON
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("id", product.getId())
                .add("name", product.getProductName())
                .add("price", product.getProductPrice())
                .add("quantity", quantity)
                .add("mimeType" , ImagesPathUtil.getMimeType(product.getProductPic()))
                .add("image", ImagesPathUtil.encodeFileToBase64(product.getProductPic()));
        return jsonObjectBuilder.build().toString();
    }*/


}
