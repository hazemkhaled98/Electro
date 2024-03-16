package com.electro.presentation.controllers;

import com.electro.presentation.dto.CustomerDTO;
import com.electro.presentation.enums.RequestAttribute;
import com.electro.services.CustomerService;
import com.electro.services.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "AdminCustomersController", urlPatterns = "/admin/customers")
public class AdminCustomersController extends HttpServlet {
   /* @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        *//*int pageNumber = Integer.parseInt(req.getParameter("pageNumber").equals("")?"0" :req.getParameter("pageNumber"));
        int pageSize = 10;*//*
        List<CustomerDTO> customerDTOS = CustomerService.getAllCustomers();
        System.out.println(customerDTOS.size());
        req.setAttribute("CustomerDTO", customerDTOS);
        //req.setAttribute("pageNumber", pageNumber);
        req.getRequestDispatcher("/jsp/listOfCustomers.jsp").forward(req, resp);
    }*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/listOfCustomers.jsp");
        req.setAttribute(RequestAttribute.PAGE_NUMBER.toString(), CustomerService.getPagesCount());
        dispatcher.forward(req, resp);
    }

}
