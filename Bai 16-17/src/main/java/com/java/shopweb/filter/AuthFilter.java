package com.java.shopweb.filter;

import com.java.shopweb.model.dto.UserDTO;
import com.java.shopweb.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter(filterName = "AuthFilter" , urlPatterns = {"/productions","/dashboard"})
public class AuthFilter implements Filter {

    private final UserService userService = new UserService();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        UserDTO loggedUser = (session != null) ? (UserDTO) session.getAttribute("loggedUser") : null;
        if (loggedUser != null) {
            chain.doFilter(request, response);
            return;
        }

        String token = getCookieValue(req,"remember_token");
        if(token != null){
            UserDTO userFromToken = userService.findByToken(token);
            if(userFromToken != null){
                HttpSession newSession = req.getSession(true);
                newSession.setAttribute("loggedUser",userFromToken);
                chain.doFilter(request,response);
                return;
            }
        }

        String loginUrl = req.getContextPath() + "/login";
        resp.sendRedirect(loginUrl+"?redirect="+req.getServletPath());
    }


    private String getCookieValue(HttpServletRequest req,String name){
        Cookie[] cookies = req.getCookies();
        if(cookies == null) return null;
        for(Cookie c: cookies){
            if(name.equals(c.getName()))
                return c.getValue();
        }
        return null;
    }
}
