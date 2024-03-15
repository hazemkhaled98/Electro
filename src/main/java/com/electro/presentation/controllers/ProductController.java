package com.electro.presentation.controllers;

import com.electro.persistence.entities.Product;
import com.electro.presentation.dto.DisplayedProductDTO;
import com.electro.presentation.enums.RequestAttribute;
import com.electro.services.ProductService;
import com.electro.services.util.FileSystemUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "ProductController", urlPatterns = "/product")
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("id"));

        Optional<Product> optionalProduct = ProductService.getProduct(productId);

        if (optionalProduct.isPresent()) {
            Product productEntity = optionalProduct.get();
            DisplayedProductDTO displayedProductDTO = DisplayedProductDTO.builder()
                    .name(productEntity.getProductName())
                    .description(productEntity.getProductDescription())
                    .price(productEntity.getProductPrice())
                    .category(productEntity.getCategory())
                    .mimeType(FileSystemUtil.getMimeType(productEntity.getProductPic()))
                    .productPic(FileSystemUtil.encodeFileToBase64(productEntity.getProductPic()))
                    .build();


            req.setAttribute(RequestAttribute.PRODUCT.toString(), displayedProductDTO);

            req.getRequestDispatcher("/jsp/product.jsp").forward(req, resp);
        } else {

            resp.sendRedirect(req.getContextPath() + "/jsp/error.html");
        }
    }
}
