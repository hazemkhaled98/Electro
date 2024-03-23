package com.electro.presentation.controllers;

import com.electro.persistence.entities.CartItem;
import com.electro.persistence.entities.Product;
import com.electro.presentation.dto.DisplayedProductDTO;
import com.electro.presentation.enums.RequestAttribute;
import com.electro.services.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;


@WebServlet(name = "productController", value = "/product")
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Optional<Product> product = ProductService.getProductByName(name);
        if(product.isPresent()){
            DisplayedProductDTO displayedProductDTO = DisplayedProductDTO.of(product.get());
            req.setAttribute(RequestAttribute.PRODUCT.toString(), displayedProductDTO);
            req.getRequestDispatcher("/jsp/product.jsp").forward(req, resp);
        } else {
            req.setAttribute(RequestAttribute.ERROR.toString(), "Product not found");
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }

}
