package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StageController {
	
	//[ステータス]ボタン押下時処理、stage→status
	@GetMapping()
	public String showStatus(Model model,int userId) {
		//userIdからUserTableEntityの情報を取得→Modelに詰める
		
		return "";
	}
	
	//[戻る]ボタン押下時処理、status→stage
	@GetMapping()
	public String toStage(int userId,Model model){
		//HelpControllerのtoStageメソッドと同じ処理（まとめてもいけるんかもしれません）
		return "";
	}
	
	//[チュートリアル]ボタン押下時処理、stage→help
	@GetMapping()
	public String toHelp() {
		
		return "";
	}
	
	@GetMapping()
	//[ログアウト]ボタン押下時処理、stage→title
	public String logput() {
		return "";
	}
	
	//ステージ選択時処理、stage→battle1
	@GetMapping()
	public String toBattle(int userId,int selectStage,Model model) {
		
		return "";
	}

}
