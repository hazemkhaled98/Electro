package com.electro.presentation.controllers;

import com.electro.persistence.entities.Customer;
import com.electro.presentation.dto.LoginDTO;
import com.electro.presentation.enums.RequestAttribute;
import com.electro.presentation.enums.SessionAttribute;
import com.electro.services.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;


@WebServlet(name = "LoginController", value = "/login/*")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session == null){
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/update");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginDTO loginDTO = LoginDTO.builder()
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .build();
        Optional<Customer> customer = CustomerService.login(loginDTO);
        if(customer.isPresent()){
            req.getSession(true).setAttribute(SessionAttribute.LOGGED_IN_CUSTOMER.toString(), customer.get());
            resp.sendRedirect("/home");
        }
        else {
            req.setAttribute(RequestAttribute.ERROR.toString(), "Invalid E-mail or password");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        }
    }
}
