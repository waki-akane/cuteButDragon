package com.example.demo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class ResultController {
	
	//もう一度ボタン押下時処理、result→battle1
	//StageControllerの同名メソッドと同じ処理（まとめてもいいのかも）
	@GetMapping()
	public String toBattle(int userId,int selectStage,Model model) {
		
		return "";
	}
	
	//[ステージ選択へ]ボタン押下時処理、result→stage
	@GetMapping()
	public String toStage(int userId,Model model){
		//HelpControllerのtoStageメソッドと同じ処理（まとめてもいけるんかもしれません）
		return "";
	}
	
	

}
