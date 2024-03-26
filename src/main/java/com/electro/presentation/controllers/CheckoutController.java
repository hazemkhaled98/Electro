package com.electro.presentation.controllers;



import com.electro.persistence.entities.Customer;
import com.electro.presentation.dto.CartItemDTO;
import com.electro.presentation.enums.RequestAttribute;
import com.electro.presentation.enums.SessionAttribute;
import com.electro.services.CartService;
import com.electro.services.OrderService;
import com.electro.services.util.EmailUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.mail.EmailException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CheckoutController", value = "/checkout")
public class CheckoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session == null){
            req.setAttribute(RequestAttribute.ERROR.toString(), "There is no cart! Go back to homepage and start adding items to your cart.");
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
            return;
        }
        Customer customer = (Customer) session.getAttribute(SessionAttribute.LOGGED_IN_CUSTOMER.toString());
        if(customer == null){
            req.setAttribute(RequestAttribute.ERROR.toString(), "Log in first then go to checkout");
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
            return;
        }
        List<CartItemDTO> cartItems = (List<CartItemDTO>) session.getAttribute(SessionAttribute.CART_ITEMS.toString());
        double total = CartService.getTotalPrice(cartItems);
        req.setAttribute(RequestAttribute.CART_ITEMS.toString(), cartItems);
        req.setAttribute(RequestAttribute.TOTAL.toString(), total);
        req.getRequestDispatcher("/jsp/checkout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session == null){
            req.setAttribute(RequestAttribute.ERROR.toString(), "There is no cart!. Go back to homepage");
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
            return;
        }
        Customer customer = (Customer) session.getAttribute(SessionAttribute.LOGGED_IN_CUSTOMER.toString());
        PrintWriter writer = resp.getWriter();
        try{
            OrderService.completeOrder(customer);
            resp.setStatus(200);
            session.setAttribute(SessionAttribute.CART_ITEMS.toString(), new ArrayList<CartItemDTO>());
            try{
                EmailUtil.sendOrderConfirmation(customer.getEmail());
            } catch (EmailException e){
                e.printStackTrace();
            }
            writer.print("Order placed successfully");
        } catch (RuntimeException e){
            resp.setStatus(409);
            writer.print(e.getMessage());
        } catch (Exception e){
            resp.setStatus(409);
            writer.print("An error has occurred while completing your order. Please try again or contact the admin");
        }
    }
}
