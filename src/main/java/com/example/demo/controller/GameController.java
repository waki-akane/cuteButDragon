package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.ActionEntity;
import com.example.demo.entity.MyMonsterEntity;
import com.example.demo.service.ActionService;
import com.example.demo.service.MyMonsterService;
import com.example.demo.service.userdetails.UserDetailsImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class GameController {
	
	@Autowired
	MyMonsterService mms;
	
	@Autowired
	ActionService as;
	
	//Battle2(技選択画面)へ
	@GetMapping("/next")
    public String toBattle(HttpSession session, Model model,int currentEmHp,int currentMmHp) {
		if(currentEmHp >= 0) {
			
			
		}
		UserDetailsImpl user = (UserDetailsImpl) session.getAttribute("user");
		int userId = user.getUserId();
		MyMonsterEntity mm = mms.findByUserId(userId);
		List<ActionEntity> actionLiist = as.imAllAction(mm.getImId().getImId());
		model.addAttribute("mm",mm);
		model.addAttribute("actionList", actionLiist);
		return "battle3";
	}

}
