package com.example.demo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.userdetails.UserDetailsImpl;

import jakarta.servlet.http.HttpSession;

public class GameController {
	
	@GetMapping("/home")
    public String home(Model model, HttpSession session) {
        UserDetailsImpl user = (UserDetailsImpl) session.getAttribute("user");
        int status = user.getUser().getStatus();
        if (user != null) {
            model.addAttribute("username", user.getUserId());
        }
        return "home";
    }

}
