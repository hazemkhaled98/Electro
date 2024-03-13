package com.electro.presentation.controllers;

import com.electro.persistence.entities.Customer;
import com.electro.presentation.dto.SignUpDTO;
import com.electro.presentation.enums.RequestAttributes;
import com.electro.presentation.enums.SessionAttributes;
import com.electro.services.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.Optional;

import static com.electro.presentation.enums.RequestAttributes.ENTERED_USER;

@WebServlet(name = "SignUpController", value = "/signup/*")
public class SignUpController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignUpDTO signUpDTO = SignUpDTO.builder()
                .name(req.getParameter("name"))
                .email(req.getParameter("email"))
                .birthdate(Date.valueOf(req.getParameter("birthdate")).toLocalDate())
                .job(req.getParameter("job"))
                .country(req.getParameter("country"))
                .city(req.getParameter("city"))
                .streetNo(req.getParameter("street_no"))
                .streetName(req.getParameter("street_name"))
                .password(req.getParameter("password"))
                .build();

        Optional<Customer> customer = CustomerService.signup(signUpDTO);
        if(customer.isPresent()){
            req.getSession(true).setAttribute(SessionAttributes.LOGGED_IN_CUSTOMER.toString(), customer.get());
            resp.sendRedirect("/home");
        }
        else {
            req.setAttribute(RequestAttributes.ERROR.toString(), "E-mail is already registered!");
            req.setAttribute(String.valueOf(RequestAttributes.ENTERED_USER), signUpDTO);
            req.getRequestDispatcher("/jsp/signup.jsp").forward(req, resp);
        }
    }
}
