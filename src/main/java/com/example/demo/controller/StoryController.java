
//不要、HrsControllerへ

//package com.example.demo.controller;

//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;

//import com.example.demo.dto.MyMonsterDTO;

//@Controller
//public class StoryController {

//	//OP、[キャラ選択へ]ボタン押下時処理、ストーリー画面からキャラ選択画面へ
//	@GetMapping("toCharcter")
//	public String toCharacter(Model model,int userId){
//		model.addAttribute(userId);
//		model.addAttribute("mmDTO", new MyMonsterDTO());
//		return "character";
//	}
//	
//	//ED、[戻る]ボタン押下時処理、endroll→stage
//	@GetMapping()
//	public String toStage(int userId,Model model){
//		//HelpControllerのtoStageメソッドと同じ処理（まとめてもいけるんかもしれません）
//		return "";
//	}
//
//}
