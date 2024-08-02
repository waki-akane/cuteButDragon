package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.ActionEntity;
import com.example.demo.entity.MyMonsterEntity;
import com.example.demo.entity.URL;
import com.example.demo.entity.UserTableEntity;
import com.example.demo.service.ActionService;
import com.example.demo.service.EnemyMonsterService;
import com.example.demo.service.MyMonsterService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StageController {

	@Autowired
	MyMonsterService mms;

	@Autowired
	EnemyMonsterService ems;
	
	@Autowired
	ActionService as;

	//[ステータス]ボタン押下時処理、stage→status
	@GetMapping("/status")
	public String showStatus(Model model, HttpSession session) {
		UserTableEntity user = (UserTableEntity) session.getAttribute("user");
		model.addAttribute("user", user);
		
		MyMonsterEntity mm = mms.findByUserId(user.getUserId());
		model.addAttribute("mm",mm);
		
		List<ActionEntity> list = as.imAllAction(mm.getIm().getImId());
		model.addAttribute("actions",list);
		
		model.addAttribute("url",URL.url);
		
		double i = 0;
		if(mm.getMmLevel() == 1) {
			i = mm.getMmEx() / 1000;
			model.addAttribute("maxEx", 1000);
		}else if(mm.getMmLevel() == 2) {
			i = mm.getMmEx() / 2000;
			model.addAttribute("maxEx", 2000);
		}else if(mm.getMmLevel() == 3) {
			i = mm.getMmEx() / 3000;
			model.addAttribute("maxEx", 3000);
		}
		model.addAttribute("i",i);
		
		return "status";
	}

	//[チュートリアル]ボタン押下時処理、stage→help
	@GetMapping("/Help")
	public String toHelp(Model model) {
		model.addAttribute("url",URL.url);
		return "help";
	}

}
