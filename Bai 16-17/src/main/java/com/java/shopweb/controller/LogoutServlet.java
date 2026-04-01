package com.java.shopweb.controller;

import com.java.shopweb.model.dto.UserDTO;
import com.java.shopweb.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "LogouServlet",urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if(session != null){
            UserDTO user = (UserDTO) session.getAttribute("loggedUser");
            if(user != null){
                userService.clearRememberToken(user.getId());
            }
            session.invalidate();
        }

        Cookie tokenCookie = new Cookie("remember_token","");
        tokenCookie.setMaxAge(0);
        tokenCookie.setPath("/");
        resp.addCookie(tokenCookie);

        Cookie emailCookie = new Cookie("saved_email","");
        emailCookie.setMaxAge(0);
        emailCookie.setPath("/");
        resp.addCookie(emailCookie);

        resp.sendRedirect(req.getContextPath()+"/login?success=loggedout");
    }

}
