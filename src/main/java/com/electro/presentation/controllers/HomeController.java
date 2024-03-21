package com.electro.presentation.controllers;


import com.electro.persistence.entities.Product;
import com.electro.presentation.dto.DisplayedProductDTO;
import com.electro.presentation.enums.RequestAttribute;
import com.electro.presentation.util.ProductDtoMapper;
import com.electro.services.ProductService;
import com.electro.services.util.ImagesPathUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@WebServlet(name = "HomeController", value = "/home")
public class HomeController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String category = req.getParameter("category");
        if (category == null) {
            category = "all";
        }
        List<Product> products = new ArrayList<>();
        switch (category) {
            case "all" -> products = ProductService.getAllProducts();
            case "laptop" -> products = ProductService.getProductsByCategory("laptop");
            case "smartphone" -> products = ProductService.getProductsByCategory("smartphone");
            case "camera" -> products = ProductService.getProductsByCategory("camera");
            default -> {
                req.setAttribute(RequestAttribute.ERROR.toString(), "Invalid category. Go back to the homepage");
                req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
            }
        }
        sendProducts(products, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String category = req.getParameter("category");
        List<Product> products = new ArrayList<>();
        switch (category) {
            case "all" -> products = ProductService.getAllProductsByName(name);
            case "laptop" -> products = ProductService.getProductsByNameAndCategory(name, "laptop");
            case "smartphone" -> products = ProductService.getProductsByNameAndCategory(name, "smartphone");
            case "camera" -> products = ProductService.getProductsByNameAndCategory(name , "camera");
            default -> {
                req.setAttribute(RequestAttribute.ERROR.toString(), "Invalid category. Go back to the homepage");
                req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
            }
        }
        sendProducts(products, req, resp);
    }

    private void sendProducts(List<Product> products, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DisplayedProductDTO> displayedProducts = mapProductToDto(products);
        req.setAttribute(RequestAttribute.PRODUCTS.toString(), displayedProducts);
        req.getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
    }

    private List<DisplayedProductDTO> mapProductToDto(List<Product> products) throws IOException {
        List<DisplayedProductDTO> displayedProducts = new ArrayList<>();
        for (Product product : products) {
            DisplayedProductDTO displayedProductDTO = ProductDtoMapper.productToDto(product);
            displayedProducts.add(displayedProductDTO);
        }
        return displayedProducts;
    }
}
