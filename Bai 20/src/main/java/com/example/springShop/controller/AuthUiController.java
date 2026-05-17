package com.example.springShop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthUiController {


    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/fake-login")
    public String fakeLoginRedirect(){
        return "redirect:/cms";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/";
    }
}
