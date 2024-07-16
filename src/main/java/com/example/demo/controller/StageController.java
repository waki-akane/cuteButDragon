package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.EnemyMonsterEntity;
import com.example.demo.entity.MyMonsterEntity;
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
		return "status";
	}

	//[チュートリアル]ボタン押下時処理、stage→help
	@GetMapping("/Help")
	public String toHelp() {
		return "help";
	}

	//stage→battle
	@GetMapping("/toBattle")
	public String toBattle(Model model, HttpSession session, int selectStage) {
		UserDetailsImpl user = (UserDetailsImpl) session.getAttribute("user");
		int userId = user.getUserId();

		MyMonsterEntity mm = mms.findByUserId(userId);

		EnemyMonsterEntity em = ems.showEm(selectStage);
		model.addAttribute("em", em);

		// 背景や敵キャラクターの情報をステージ番号に応じて追加
		//画像は仮のファイル名です
		switch (selectStage) {
		case 1:
			model.addAttribute("background", "bg1.jpg");//背景
			model.addAttribute("enemy", "em1.png");//敵モンスター
			break;
		case 2:
			model.addAttribute("background", "bg2.jpg");
			model.addAttribute("enemy", "em2.png");
			break;
		case 3:
			model.addAttribute("background", "bbg3.jpg");
			model.addAttribute("enemy", "em3.png");
			break;
		}
		return "battle1";
	}

}
