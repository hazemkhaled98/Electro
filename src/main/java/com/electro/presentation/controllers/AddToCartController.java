package com.electro.presentation.controllers;


import com.electro.presentation.dto.AddToCartRespDTO;
import com.electro.presentation.dto.CartItemDTO;
import com.electro.presentation.enums.SessionAttribute;
import com.electro.services.CartService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "AddToCartController", urlPatterns = "/addToCart")

public class AddToCartController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getParameter("productName");
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(true);
        boolean resultFromAdding=CartService.addToCart(session, productName, quantity);
        List<CartItemDTO> cartItems = (List<CartItemDTO>) session.getAttribute(SessionAttribute.CART_ITEMS.name());
        AddToCartRespDTO response=AddToCartRespDTO.builder().build();

        if (resultFromAdding && cartItems!=null) {
            response.setMessage("success");
            response.setCartItemsCount(cartItems.size());
        }
        else {
            response.setMessage("error");
        }

        Gson gson = new Gson();
        String json = gson.toJson(response);
        resp.getWriter().write(json);
    }
}
