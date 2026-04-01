package com.java.shopweb.controller;

import com.java.shopweb.model.dto.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.eclipse.tags.shaded.org.apache.regexp.RE;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

@WebServlet(name="DashboardServlet",urlPatterns = "/dashboard")
public class DashboardServlet extends HttpServlet {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        UserDTO user = (session != null) ? ( UserDTO) session.getAttribute("loggedUser") : null ;

        if(session !=null){
            req.setAttribute("sessionId",session.getId());
            req.setAttribute("sessionCreated",new java.util.Date(session.getCreationTime()).toString());
            req.setAttribute("sessionAccessed",new java.util.Date(session.getLastAccessedTime()).toString());
            req.setAttribute("sessionTimeout",session.getMaxInactiveInterval()/60);
        }

        if(user != null && user.getCreatedAt() !=null){
            req.setAttribute("createdStr",user.getCreatedAt().format(FMT));
        }

        if(user !=null && user.getFullName() != null && !user.getFullName().isEmpty()){
            req.setAttribute("avatarInitial",String.valueOf(user.getFullName().charAt(0)).toUpperCase());
        } else {
            req.setAttribute("avatarInitial","U");
        }

        req.setAttribute("cookies",req.getCookies());

        Object vc = getServletContext().getAttribute("visitCount");
        req.setAttribute("user",user);

        getServletContext().getRequestDispatcher("/WEB-INF/views/auth/dashboard.jsp")
                .forward(req,resp);
    }
}
