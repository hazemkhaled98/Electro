package com.electro.presentation.controllers;

import com.electro.presentation.enums.RequestAttribute;
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
        boolean isDeleted = ProductService.deleteProduct(productId);
        if(isDeleted){
            req.setAttribute(RequestAttribute.SUCCESS.toString(), "Product is deleted successfully");
        }else{
            req.setAttribute(RequestAttribute.ERROR.toString(), "Failed to delete product");
        }
        req.getRequestDispatcher("/admin/products").forward(req, resp);
    }
}
