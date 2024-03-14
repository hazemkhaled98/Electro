package com.electro.presentation.controllers;

import com.electro.presentation.enums.RequestAttribute;
import com.electro.services.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "AdminProductsController", urlPatterns = "/admin/products")
public class AdminProductsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/listOfProducts.jsp");
        req.setAttribute(RequestAttribute.PAGES_COUNT.toString(), ProductService.getPagesCount());
        dispatcher.forward(req, resp);
    }
}
