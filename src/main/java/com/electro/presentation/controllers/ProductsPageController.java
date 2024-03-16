package com.electro.presentation.controllers;

import com.electro.persistence.entities.Product;
import com.electro.services.ProductService;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "ProductsPageController", urlPatterns = "/admin/products-page")
public class ProductsPageController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("inside do get of products page");
        List<Product> products = ProductService.getPageOfProduct(req.getParameter("pg"));
        List<String> productsJson = new ArrayList<>();
        for(Product product : products){
            productsJson.add(productToJson(product));
        }
        PrintWriter out = resp.getWriter();
        out.print(productsJson);
        out.flush();
    }

    private String productToJson(Product product) {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add( "id",product.getId() )
                .add( "name", product.getProductName() )
                .add( "description" ,product.getProductDescription())
                .add( "quantity", product.getStockQuantity() )
                .add( "price", product.getProductPrice())
                .add( "category",product.getCategory());
        return jsonObjectBuilder.build().toString();
    }

}
