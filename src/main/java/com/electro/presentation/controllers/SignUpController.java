package com.electro.presentation.controllers;

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

import static com.electro.presentation.enums.RequestAttributes.ENTERED_USER;

@WebServlet(name = "SignUpController", value = "/signup/*")
public class SignUpController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignUpDTO signUpDTO = new SignUpDTO();

        signUpDTO.setName(req.getParameter("name"));
        signUpDTO.setEmail(req.getParameter("email"));
        signUpDTO.setBirthdate(Date.valueOf(req.getParameter("birthdate")).toLocalDate());
        signUpDTO.setJob(req.getParameter("job"));
        signUpDTO.setCountry(req.getParameter("country"));
        signUpDTO.setCity(req.getParameter("city"));
        signUpDTO.setStreetNo(req.getParameter("street_no"));
        signUpDTO.setStreetName(req.getParameter("street_name"));
        signUpDTO.setPassword(req.getParameter("password"));

        if(CustomerService.signup(signUpDTO)){

            req.getSession(true).setAttribute(SessionAttributes.LOGGED_IN_CUSTOMER.toString(), signUpDTO);
            resp.sendRedirect("/home");
        }
        else {
            req.setAttribute("errorMessage", "Email is already exist");
            req.setAttribute(String.valueOf(RequestAttributes.ENTERED_USER), signUpDTO);
            req.getRequestDispatcher("/jsp/signup.jsp").forward(req, resp);
        }
    }
}
