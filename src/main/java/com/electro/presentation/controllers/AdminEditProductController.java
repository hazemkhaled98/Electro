package com.electro.presentation.controllers;

import com.electro.persistence.entities.Product;
import com.electro.presentation.dto.DisplayedProductDTO;
import com.electro.presentation.enums.RequestAttribute;
import com.electro.presentation.util.ProductDtoMapper;
import com.electro.services.ProductService;
import com.electro.services.enums.FileType;
import com.electro.services.util.ImagesPathUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;


@WebServlet(name = "AdminEditProductController", value = "/admin/products/edit")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class AdminEditProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productId");
        Optional<Product> product = ProductService.getProductById(productId);
        if(product.isPresent()){
            DisplayedProductDTO displayedProductDTO = ProductDtoMapper.productToDto(product.get());
            req.setAttribute(RequestAttribute.PRODUCT.toString(), displayedProductDTO);
            req.setAttribute("productId", productId);
            req.getRequestDispatcher("/jsp/editProduct.jsp").forward(req, resp);
        } else {
            req.setAttribute(RequestAttribute.ERROR.toString(), "Product is not present");
            req.getRequestDispatcher("/admin/products").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String productId = req.getParameter("idEdit");
        Optional<Product> oldProduct = ProductService.getProductById(productId);

        if (oldProduct.isEmpty()) {
            req.setAttribute(RequestAttribute.ERROR.toString(), "Product is not present");
        } else {
            Part filePart = req.getPart("productPhotoEdit");
            String productName = req.getParameter("nameEdit");
            String description = req.getParameter("descriptionEdit");
            String category = req.getParameter("categoryEdit");

            int quantity = Integer.parseInt(req.getParameter("quantityEdit"));
            double price = Double.parseDouble(req.getParameter("priceEdit"));

            Product product = oldProduct.get();
            product.setId(Integer.parseInt(productId));
            product.setProductName(productName);
            product.setProductDescription(description);
            product.setStockQuantity(quantity);
            product.setProductPrice(BigDecimal.valueOf(price));
            product.setCategory(category);
            if(filePart != null && filePart.getSize() > 0){
                String productPicPath = ImagesPathUtil.storeFileFromPart(filePart,
                        productId, FileType.PRODUCT_PIC);
                product.setProductPic(productPicPath);
            }
            Optional<Product> updatedProduct = ProductService.updateProduct(product);

            if (updatedProduct.isPresent()) {
                req.setAttribute(RequestAttribute.SUCCESS.toString(), "The Product is updated successfully");
            } else {
                req.setAttribute(RequestAttribute.ERROR.toString(), "Failed to update the product");
            }
            req.getRequestDispatcher("/admin/products").forward(req, resp);
        }
    }
}
