package com.electro.presentation.controllers;

import com.electro.persistence.entities.Product;
import com.electro.presentation.enums.RequestAttribute;
import com.electro.services.ProductService;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;


@WebServlet(name = "AdminEditProductController", value = "/admin/products/edit")
public class AdminEditProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productId");
        Optional<Product> product = ProductService.getProductById(productId);
        if(product.isPresent()){
            req.setAttribute(RequestAttribute.PRODUCT.toString(), product.get());
            req.getRequestDispatcher("/jsp/editProduct.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/admin/products");
        }
    }
}
