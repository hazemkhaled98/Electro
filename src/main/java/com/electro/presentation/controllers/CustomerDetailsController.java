package com.electro.presentation.controllers;

import com.electro.persistence.entities.Order;
import com.electro.presentation.dto.CustomerDTO;
import com.electro.presentation.dto.OrderDTO;
import com.electro.presentation.dto.OrderItemDTO;
import com.electro.services.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerDetailsController", urlPatterns = "/customerDetails")
public class CustomerDetailsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDTO customer = new CustomerDTO();
        Integer customerID = Integer.parseInt(req.getParameter("customerID"));
        System.out.println(customerID);
        customer = CustomerService.getCustomerById(customerID);
        List<OrderDTO> orderDTOS = CustomerService.getCustomerOrders(customerID);
       /* List<OrderItemDTO> orderItemDTOS = new ArrayList<>();
        for (int i = 0; i < orderDTOS.size(); i++) {
            orderItemDTOS.add(CustomerService.getOrderItemsForOrder(orderDTOS.get(i).getId()).get(0));
        }*/
        req.setAttribute("orderDTOS", orderDTOS);
        req.setAttribute("customer", customer);
        req.getRequestDispatcher("/jsp/customer.jsp").forward(req, resp);
    }
}
