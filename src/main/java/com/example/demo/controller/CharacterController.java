package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

<<<<<<< HEAD
import com.example.demo.dto.MyMonsterDTO;
import com.example.demo.entity.UserTableEntity;
import com.example.demo.service.MyMonsterService;
import com.example.demo.service.userdetails.UserDetailsImpl;

import jakarta.servlet.http.HttpSession;
=======
import com.example.demo.service.MyMonsterService;
>>>>>>> stash

@Controller
public class CharacterController {
<<<<<<< HEAD

	@Autowired
	MyMonsterService mms;

	//[ルール説明へ]ボタン押下時処理、キャラ選択情報DBへ登録
	@GetMapping("toHelp")
	public String addCharacter(MyMonsterDTO mmDTO,HttpSession session) {
		UserDetailsImpl user = (UserDetailsImpl) session.getAttribute("user");
		UserTableEntity ut = user.getUser();
		mms.createMm(mmDTO,ut);
		return "help";
=======
	
	@Autowired
	MyMonsterService mms;
	
	//キャラDBへの新規登録・helpへ
	@GetMapping("addChar")
	public String addCharacter(Integer newUserId,) {
		
		return "";
>>>>>>> stash
	}
<<<<<<< HEAD
=======
	

>>>>>>> stash
}
