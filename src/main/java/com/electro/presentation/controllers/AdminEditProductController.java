package com.electro.presentation.controllers;

import com.electro.persistence.entities.Product;
import com.electro.presentation.enums.RequestAttribute;
import com.electro.services.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;


@WebServlet(name = "AdminEditProductController", value = "/admin/products/edit")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class AdminEditProductController extends HttpServlet {

    private String productName;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productId");
        Optional<Product> product = ProductService.getProductById(productId);
        if(product.isPresent()){
            productName = product.get().getProductName();
            req.setAttribute(RequestAttribute.PRODUCT.toString(), product.get());
            req.getRequestDispatcher("/jsp/editProduct.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/admin/products");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Part filePart = req.getPart("productPhotoEdit");

        String productName = req.getParameter("nameEdit");

        saveProductImage(filePart, productName);

        int productId = Integer.parseInt(req.getParameter("idEdit"));

        String description = req.getParameter("descriptionEdit");
        int quantity = Integer.parseInt(req.getParameter("quantityEdit"));
        double price = Double.parseDouble(req.getParameter("priceEdit"));
        String category = req.getParameter("categoryEdit");

        Product product = new Product();

        product.setId(productId);
        product.setProductName(productName);
        product.setProductDescription(description);
        product.setStockQuantity(quantity);
        product.setProductPrice(BigDecimal.valueOf(price));
        product.setCategory(category);

        Optional<Product> updatedProduct = ProductService.updateProduct(product);

        if(updatedProduct.isPresent()){
            req.setAttribute(RequestAttribute.SUCCESS.toString(), "Product Updated Successfully");
        } else {
            req.setAttribute(RequestAttribute.ERROR.toString(), "Failed Updating The Product");
        }
            req.getRequestDispatcher("/admin/products").forward(req, resp);

    }

    private void saveProductImage(Part filePart, String newProductName) {
        Path oldPath = Paths.get(getServletContext().getRealPath("/img/products") + File.separator + productName + ".png");
        Path uploadPath = Paths.get(getServletContext().getRealPath("/img/products") + File.separator + newProductName + ".png");
        if(filePart != null && filePart.getSize() > 0) {
            try (InputStream input = filePart.getInputStream()) {
                Files.delete(oldPath);
                Files.copy(input, uploadPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                Files.move(oldPath, uploadPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
