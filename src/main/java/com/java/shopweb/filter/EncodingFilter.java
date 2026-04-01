package com.java.shopweb.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "EncodingFilter",urlPatterns = "/*")
public class EncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
          request.setCharacterEncoding("UTF-8");
          response.setCharacterEncoding("UTF-8");
          if(response instanceof HttpServletResponse httpResp){
              httpResp.setHeader("Contenet-Type-Options","nosniff");
          }
          chain.doFilter(request,response);
    }
}
