package com.electro.presentation.controllers;


import com.electro.persistence.entities.Customer;
import com.electro.persistence.entities.Product;
import com.electro.services.CartService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import static com.electro.services.CartService.addToTemporaryCart;

@WebServlet(name = "AdminController", urlPatterns = "/addToCart")
public class AddToCartController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getParameter("productName");
        int quantity=Integer.parseInt(req.getParameter("quantity"));

        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        HttpSession session = req.getSession(true);
        if (session.getAttribute("LOGGED_IN_CUSTOMER") == null) {

            if (CartService.addToTemporaryCart(session,productName,quantity))
                out.print("success");
            else
                out.print("error");
        }
        else {
            Customer customer = (Customer) session.getAttribute("LOGGED_IN_CUSTOMER");
            if(CartService.addToPermanentCart(customer,productName,quantity))
                out.print("success");
            else
                out.print("error");
        }
    }
}
