package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dto.MyMonsterDTO;
import com.example.demo.service.MyMonsterService;
import com.example.demo.service.UsertableService;

@Controller
public class CharacterController {
	
	@Autowired
	MyMonsterService mms;
	
	@Autowired
	UsertableService uts;
	
	//キャラDBへの新規登録・helpへ
	@GetMapping("toCharcter")
	public String toCharacter(Model model){
		model.addAttribute("mmDTO", new MyMonsterDTO());
		return "character";
	}
	
	
	@GetMapping("toStage")
	public String addCharacter(MyMonsterDTO mmDTO) {
		mms.createMm(mmDTO);
		return "help";
	}
	
	
	
	
	

}
