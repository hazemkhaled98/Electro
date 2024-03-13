package com.electro.presentation.controllers;

import com.electro.persistence.entities.Customer;
import com.electro.presentation.dto.UpdateProfileDTO;
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
        UpdateProfileDTO profileDTO = new UpdateProfileDTO();
        profileDTO.setName(req.getParameter("name"));
        profileDTO.setJob(req.getParameter("job"));
        profileDTO.setCountry(req.getParameter("country"));
        profileDTO.setCity(req.getParameter("city"));
        profileDTO.setStreetNo(req.getParameter("streetNo"));
        profileDTO.setStreetName(req.getParameter("streetName"));
        try {
            profileDTO.setCreditLimit(new BigDecimal(req.getParameter("creditLimit")));
        } catch (NumberFormatException e) {
            profileDTO.setCreditLimit(BigDecimal.valueOf(-1));
        }
        Customer sessionCustomer = (Customer) req.getSession().getAttribute(SessionAttributes.LOGGED_IN_CUSTOMER.toString());
        Customer customer = CustomerService.updateProfile(profileDTO, sessionCustomer);
        System.out.println(customer.getCustomerName());
        if(customer != null){
            req.getSession(true).setAttribute(SessionAttributes.LOGGED_IN_CUSTOMER.toString(), customer);
            resp.sendRedirect("/home");
        }
        else {
            resp.sendRedirect("/jsp/profile.jsp");
        }
    }
}
