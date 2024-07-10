package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HelpController {
	
	//stageへ
	@GetMapping("toStage")
	public String toStage(int userId) {
		
		return "stage";
	}

}
