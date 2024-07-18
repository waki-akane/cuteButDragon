package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.URL;
import com.example.demo.service.EnemyMonsterService;
import com.example.demo.service.MyMonsterService;
import com.example.demo.service.userdetails.UserDetailsImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class StageController {

	@Autowired
	MyMonsterService mms;

	@Autowired
	EnemyMonsterService ems;

	//[ステータス]ボタン押下時処理、stage→status
	@GetMapping("/status")
	public String showStatus(Model model, HttpSession session) {
		UserDetailsImpl user = (UserDetailsImpl) session.getAttribute("user");
		int status = user.getStatus();
		model.addAttribute("status", status);
		
		model.addAttribute("url",URL.url);
		
		return "status";
	}

	//[チュートリアル]ボタン押下時処理、stage→help
	@GetMapping("/Help")
	public String toHelp(Model model) {
		model.addAttribute("url",URL.url);
		return "help";
	}

	//stage→battle
//	@GetMapping("/toBattle")
//	public String toBattle(Model model, HttpSession session, int selectStage) {
//		UserDetailsImpl user = (UserDetailsImpl) session.getAttribute("user");
//		int userId = user.getUserId();
//
//		MyMonsterEntity mm = mms.findByUserId(userId);
//		model.addAttribute("mm",mm);
//
//		EnemyMonsterEntity em = ems.showEm(selectStage);
//		model.addAttribute("em", em);
//
//		return "battle/battle1";
//	}

}
