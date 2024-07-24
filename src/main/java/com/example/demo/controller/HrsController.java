package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dto.MyMonsterDTO;
import com.example.demo.entity.EnemyMonsterEntity;
import com.example.demo.entity.MyMonsterEntity;
import com.example.demo.entity.URL;
import com.example.demo.entity.UserTableEntity;
import com.example.demo.service.EnemyMonsterService;
import com.example.demo.service.MyMonsterService;
import com.example.demo.service.userdetails.UserDetailsImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class HrsController {

	@Autowired
	MyMonsterService mms;
	
	@Autowired
	EnemyMonsterService ems;

	//[閉じる]ボタン押下時処理、ルール説明画面からステージ選択画面へ
	@GetMapping("/toStage")
	public String toStage(Model model, HttpSession session) {
		UserDetailsImpl user = (UserDetailsImpl) session.getAttribute("user");
		UserTableEntity ut = user.getUser();
		MyMonsterEntity mm = mms.findByUserId(ut.getUserId());
		
		model.addAttribute("user", ut);
		model.addAttribute("mm",mm);
		model.addAttribute("url",URL.url);

		return "stage"; //stageページにリダイレクト
	}

	//もう一度ボタンを押したとき、resultからbattle1へ
	//stage選択時処理、stage→battle1
	@GetMapping("/toBattle")
	public String toBattle1(Model model, HttpSession session, int selectStage) {
		UserDetailsImpl user = (UserDetailsImpl) session.getAttribute("user");
		int userId = user.getUserId();
		MyMonsterEntity mm = mms.findByUserId(userId);
		model.addAttribute("mm", mm);
		
		EnemyMonsterEntity em = ems.showEm(selectStage);
		model.addAttribute("em",em);
		
		model.addAttribute("url",URL.url);

		// StageControllerの同名メソッドと同じ処理を行う
		// ここで必要なデータの準備などを行う
		return "battle/battle1";
	}
	

	//OP、[キャラ選択へ]ボタン押下時処理、ストーリー画面からキャラ選択画面へ
	@GetMapping("/toCharcter")
	public String toCharacter(Model model) {
		model.addAttribute("mmDTO", new MyMonsterDTO());
		
		model.addAttribute("url",URL.url);
		
		return "character";
	}

	//ED、[戻る]ボタン押下時処理、endroll→login
	//@GetMapping() 
	//public String toLogin(int userId, Model model) {
	//HelpControllerのtoStageメソッドと同じ処理（まとめてもいけるんかもしれません）
	//return "";
	//}

}
