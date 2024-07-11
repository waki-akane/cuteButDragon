package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dto.MyMonsterDTO;

@Controller
public class HrsController {

	//[閉じる]ボタン押下時処理、ルール説明画面からステージ選択画面へ
	//[ステージ選択へ]ボタン押下時処理、result→stage	
	public String toStage(int userId, Model model) {
		//ステージ選択ボタンの表示をステータスによって変更したい
		//→HTMLファイルで分岐処理になるかと思うのでModelにuserIdから取得したUserTableEntityをつめてください
		return "";
	}

	//もう一度ボタン押下時処理、result→battle1
	//StageControllerの同名メソッドと同じ処理（まとめてもいいのかも）
	@GetMapping()
	public String toBattle(int userId, int selectStage, Model model) {

		return "";
	}

	//OP、[キャラ選択へ]ボタン押下時処理、ストーリー画面からキャラ選択画面へ
	@GetMapping("toCharcter")
	public String toCharacter(Model model, int userId) {
		model.addAttribute(userId);
		model.addAttribute("mmDTO", new MyMonsterDTO());
		return "character";
	}

	//ED、[戻る]ボタン押下時処理、endroll→login
	@GetMapping()
	public String toLogin(int userId, Model model) {
		//HelpControllerのtoStageメソッドと同じ処理（まとめてもいけるんかもしれません）
		return "";
	}

}
