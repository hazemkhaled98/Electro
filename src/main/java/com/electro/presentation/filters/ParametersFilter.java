package com.electro.presentation.filters;

import com.electro.presentation.enums.RequestAttribute;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebFilter(filterName = "ParametersFilter", urlPatterns = "/*")
public class ParametersFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse  resp = (HttpServletResponse) response;
        var parameters = req.getParameterMap();
        for (var parameter : parameters.keySet()) {
            var value = req.getParameter(parameter);
            if(value == null || value.isBlank()){
                req.setAttribute(RequestAttribute.ERROR.toString(), "Invalid parameters. Go back to the homepage");
                req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
