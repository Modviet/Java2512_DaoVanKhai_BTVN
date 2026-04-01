package com.java.shopweb.controller;

import com.java.shopweb.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null && session.getAttribute("loggedUser") != null){
            resp.sendRedirect(req.getContextPath()+"/dashboard");
            return;
        }
        getServletContext()
                .getRequestDispatcher("/WEB-INF/views/auth/register.jsp")
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String comfirmPassword = req.getParameter("confirmPassword");
        String gender = req.getParameter("gender");

        if(email == null|| email.isBlank() || password == null || password.isBlank()){
            req.setAttribute("errorMsg","Vui long dien day du thong tin!");
            forwardBack(req,resp,fullName,email,gender);
            return;
        }

        if(!password.equals(comfirmPassword)){
            req.setAttribute("errorMsg","Mat khau khong dung");
            forwardBack(req,resp,fullName,email,gender);
            return;
        }

        if(password.length() < 6){
            req.setAttribute("errorMsg","Mat khau phai co it nhat 6 ky tu!");
            forwardBack(req,resp,fullName,email,gender);
            return;
        }

        int newId = userService.register(fullName,email,password,gender);
        if(newId ==-1){
            req.setAttribute("errorMsg","Email da duoc su dung! Vui long chon email khac");
            forwardBack(req,resp,fullName,email,gender);
            return;
        }
        resp.sendRedirect(req.getContextPath()+"/login?success=registered");
    }

    private void forwardBack(HttpServletRequest req,HttpServletResponse resp,
                             String fullName,String email,String gender)
            throws ServletException,IOException {
        req.setAttribute("fullName",fullName);
        req.setAttribute("email",email);
        req.setAttribute("gender",gender);
        getServletContext()
                .getRequestDispatcher("/WEB-INF/views/auth/register.jsp")
                .forward(req,resp);
    }
}
