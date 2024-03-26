package com.electro.presentation.controllers;


import com.electro.services.util.EmailUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.mail.Email;

import java.io.IOException;



@WebServlet(name = "NewsletterController", urlPatterns = "/newsletter")
public class NewsletterController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("newsletter");
        String email = req.getParameter("email");
        try{
            EmailUtil.subscribeToNewsletter(email);
            resp.setStatus(200);
            resp.getWriter().write("Thank you for subscribing to our newsletter");
        } catch (Exception e){
            resp.setStatus(400);
            resp.getWriter().write("An error has occurred while subscribing to our newsletter");
        }
    }
}
