package com.electro.presentation.controllers;

import com.electro.persistence.entities.Product;
import com.electro.presentation.dto.CreatedProductDTO;
import com.electro.presentation.enums.RequestAttribute;
import com.electro.services.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;


@WebServlet(name = "AdminAddProductController", urlPatterns = "/admin/add-product")
@MultipartConfig
public class AdminAddProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/addProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CreatedProductDTO createdProductDTO = CreatedProductDTO.builder()
                .name(req.getParameter("name"))
                .description(req.getParameter("description"))
                .quantity(Integer.valueOf(req.getParameter("quantity")))
                .price(new BigDecimal(req.getParameter("price")))
                .category(req.getParameter("category"))
                .filePart(req.getPart("productImg"))
                .build();

        Optional<Product> product = ProductService.addProduct(createdProductDTO);
        if(product.isPresent()){
            req.setAttribute(RequestAttribute.SUCCESS.toString(),"The Product is created successfully");
        }
        else {
            req.setAttribute(RequestAttribute.ERROR.toString(),"Fail to create the product");
        }

        req.getRequestDispatcher("/jsp/addProduct.jsp").forward(req,resp);
    }
}
