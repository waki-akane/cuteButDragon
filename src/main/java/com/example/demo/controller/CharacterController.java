package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.MyMonsterService;

@Controller
public class CharacterController {
	
	@Autowired
	MyMonsterService mms;
	
	//キャラDBへの新規登録・helpへ
	@GetMapping("addChar")
	public String addCharacter(Integer newUserId) {
		
		return "";
	}
	

}
