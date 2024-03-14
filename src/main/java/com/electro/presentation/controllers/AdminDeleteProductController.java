package com.electro.presentation.controllers;

import com.electro.services.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "AdminDeleteProductController", value = "/admin/products/delete")
public class AdminDeleteProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productId");
        ProductService.deleteProduct(productId);
        resp.sendRedirect("/admin/products");
    }
}
