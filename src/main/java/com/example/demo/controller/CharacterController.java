package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dto.MyMonsterDTO;
import com.example.demo.service.MyMonsterService;

@Controller
public class CharacterController {

	@Autowired
	MyMonsterService mms;

	//[ルール説明へ]ボタン押下時処理、キャラ選択情報DBへ登録
	@GetMapping("toHelp")
	public String addCharacter(MyMonsterDTO mmDTO, Model model) {
		mms.createMm(mmDTO);
		model.addAttribute(mmDTO.getUserId());
		return "help";
	}
}
