package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.userdetails.UserDetailsImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class StageController {

	//[ステータス]ボタン押下時処理、stage→status
	@GetMapping("/status")
	public String showStatus(Model model, HttpSession session) {
		UserDetailsImpl user = (UserDetailsImpl) session.getAttribute("user");
		int status = user.getUser().getStatus();
		model.addAttribute("userId", user.getUserId());
		return "status";
	}

	//[チュートリアル]ボタン押下時処理、stage→help
	@GetMapping("/Help")
	public String toHelp() {
		return "help";
	}

}
