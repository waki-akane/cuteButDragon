package com.example.demo.controller;

import org.springframework.ui.Model;

public class LoginController {
	
	
	public String login(Model model) {
		
		return "stage";
		
	}
	
	public String addUser(Model model) {
		
		return "user";
	}
	
	public String newUser(Model model) {
		
		return "story";
	}

}
