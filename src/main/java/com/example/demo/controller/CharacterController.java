package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.MyMonsterDTO;
import com.example.demo.entity.URL;
import com.example.demo.entity.UserTableEntity;
import com.example.demo.service.MyMonsterService;
import com.example.demo.service.userdetails.UserDetailsImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class CharacterController {

	@Autowired
	MyMonsterService mms;

	//[ルール説明へ]ボタン押下時処理、キャラ選択情報DBへ登録
	@PostMapping("toHelp")
	public String addCharacter(MyMonsterDTO mmDTO,HttpSession session,Model model) {
		UserDetailsImpl user = (UserDetailsImpl) session.getAttribute("user");
		UserTableEntity ut = user.getUser();
		mms.createMm(mmDTO,ut);
		model.addAttribute("url",URL.url);
		return "help";
	}
}
