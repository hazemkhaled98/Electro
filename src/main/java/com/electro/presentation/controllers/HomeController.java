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
import java.util.List;


@WebServlet(name = "HomeController", value = "/home")
public class HomeController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String category = req.getParameter("category");
        List<Product> products = new ArrayList<>();
        switch (category) {
            case "all" -> products = ProductService.getAllProducts();
            case "laptop" -> products = ProductService.getProductsByCategory("laptop");
            case "smartphone" -> products = ProductService.getProductsByCategory("smartphone");
            case "camera" -> products = ProductService.getProductsByCategory("camera");
            default -> {
                // TODO FORWARD TO ERROR PAGE
            }
        }
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
