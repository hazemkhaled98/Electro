package com.electro.presentation.controllers;

import com.electro.persistence.entities.Customer;
import com.electro.presentation.enums.RequestAttribute;
import com.electro.services.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;


@WebServlet(name = "CheckEmailController", urlPatterns = "/checkEmail")
public class CheckEmailController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        String checkedEmail = req.getParameter("email");

        Optional<Customer> customer = CustomerService.getCustomer(checkedEmail);
        if (customer.isPresent()){

            out.print("E-mail is already registered!");
        }
        out.print("");
    }
}
