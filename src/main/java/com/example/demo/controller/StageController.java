package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class StageController {
	
	public String showStage(Model model) {
		
		return "stage";
	}

}
