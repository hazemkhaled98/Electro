package com.electro.presentation.controllers;

import com.electro.persistence.entities.Customer;
import com.electro.persistence.entities.Product;
import com.electro.services.CustomerService;
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

@WebServlet(name = "CustomerPageController", urlPatterns = "/admin/customers-page")
public class CustomerPageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("inside do get of customer page");
        List<Customer> customers = CustomerService.getPageOfCustomers(req.getParameter("pg"));
        System.out.println("customers size: gowa el doGET " + customers.size());
        List<String> customersJson = new ArrayList<>();
        try{
            for(Customer customer : customers){
                customersJson.add(customerToJson(customer));
            }
            PrintWriter out = resp.getWriter();
            System.out.println("customersJson: " + customersJson.size());
            out.print(customersJson);
            out.flush();
        }
        catch (Exception e) {
            System.out.println("error in customer page controller");
            e.printStackTrace();
        }

    }

    private String customerToJson( Customer customer ) {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add( "id",customer.getId() )
                .add( "customerName", customer.getCustomerName() )
                .add( "birthday" , String.valueOf(customer.getBirthday()))
                .add( "password", customer.getPassword())
                .add( "job" ,customer.getJob())
                .add( "email", customer.getEmail() )
                .add("creditLimit" , customer.getCreditLimit())
                .add( "city", customer.getCity())
                .add( "country",customer.getCountry())
                .add( "streetNo",customer.getStreetNo())
                .add( "streetName",customer.getStreetName());




        return jsonObjectBuilder.build().toString();
    }

}
