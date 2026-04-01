package com.java.shopweb.controller;

import com.java.shopweb.model.dto.UserDTO;
import com.java.shopweb.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name="LoginServlet",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null && session.getAttribute("loggedUser")!= null){
            resp.sendRedirect(req.getContextPath()+"/dashboard");
            return;
        }
        getServletContext()
                .getRequestDispatcher("/WEB-INF/views/auth/login.jsp")
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("rememberMe");
        String redirectUrl = req.getParameter("redirect");

        UserDTO user = userService.login(email,password);

        if(user == null){
            req.setAttribute("errorMsg","Email hoac mat khau khong dung!");
            req.setAttribute("email",email);
            getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/auto/login.jsp")
                    .forward(req,resp);
            return;
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("loggedUser",user);
        session.setMaxInactiveInterval(30 * 60);

        if("on".equals(rememberMe)){
            String token = UUID.randomUUID().toString();
            userService.saveRememberToken(user.getId(),token);

            Cookie cookie = new Cookie("remember_token",token);
            cookie.setMaxAge(7*24*60*60);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            resp.addCookie(cookie);

            Cookie emailCookie = new Cookie("saved_email",email);
            emailCookie.setMaxAge(7*24*60*60);
            emailCookie.setPath("/");
            resp.addCookie(emailCookie);
        }

        if(redirectUrl != null && !redirectUrl.isBlank()){
            resp.sendRedirect(req.getContextPath()+ redirectUrl);
        } else {
            resp.sendRedirect(req.getContextPath()+ "/dashboard");
        }
    }
}
