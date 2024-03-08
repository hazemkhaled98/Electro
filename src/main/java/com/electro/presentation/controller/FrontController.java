package com.electro.presentation.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class FrontController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String url = req.getPathInfo();
        System.out.println(url);
        if(url.equals("/login"))
                getServletContext().getNamedDispatcher("LoginServlet").forward(req, res);
        else {
            // TODO forward to error page
            System.out.println("Not Found");
        }
    }
}
