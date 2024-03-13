package com.electro.presentation.controllers;

import com.electro.persistence.entities.Customer;
import com.electro.presentation.dto.UpdateProfileDTO;
import com.electro.presentation.enums.RequestAttributes;
import com.electro.presentation.enums.SessionAttributes;
import com.electro.services.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@WebServlet(name = "UpdateProfileController", value = "/update/*")
public class UpdateProfileController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UpdateProfileDTO profileDTO = UpdateProfileDTO.builder()
                .name(req.getParameter("name"))
                .job(req.getParameter("job"))
                .country(req.getParameter("country"))
                .city(req.getParameter("city"))
                .streetNo(req.getParameter("streetNo"))
                .streetName(req.getParameter("streetName"))
                .creditLimit(parseCreditLimit(req.getParameter("creditLimit")))
                .build();
        Customer sessionCustomer = (Customer) req.getSession().getAttribute(SessionAttributes.LOGGED_IN_CUSTOMER.toString());
        Optional<Customer> customer = CustomerService.updateProfile(profileDTO, sessionCustomer);
        if(customer.isPresent()){
            req.getSession(false).setAttribute(SessionAttributes.LOGGED_IN_CUSTOMER.toString(), customer.get());
            resp.sendRedirect("/home");
        } else {
            req.setAttribute(RequestAttributes.ERROR.toString(), "Error updating information");
            req.getRequestDispatcher("/jsp/profile.jsp").forward(req, resp);
        }
    }

    private BigDecimal parseCreditLimit(String creditLimit) {
        try {
            return new BigDecimal(creditLimit);
        } catch (NumberFormatException e) {
            return BigDecimal.valueOf(-1);
        }
    }
}
