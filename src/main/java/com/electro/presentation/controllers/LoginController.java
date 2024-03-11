package com.electro.presentation.controllers;

import com.electro.persistence.entities.Customer;
import com.electro.presentation.dto.LoginDTO;
import com.electro.presentation.enums.SessionAttributes;
import com.electro.services.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;


@WebServlet(name = "LoginController", value = "/login/*")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail(req.getParameter("email"));
        loginDTO.setPassword(req.getParameter("password"));
        Optional<Customer> customer = CustomerService.login(loginDTO);
        if(customer.isPresent()){
            req.getSession(true).setAttribute(SessionAttributes.LOGGED_IN_CUSTOMER.toString(), customer.get());
            resp.sendRedirect("/home");
        }
        else {
            resp.sendRedirect("/login");
        }
    }
}
