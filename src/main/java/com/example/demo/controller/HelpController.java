package com.example.demo.controller;

import org.springframework.ui.Model;

public class HelpController {
	
	//[閉じる]ボタン押下時処理、ルール説明画面からステージ選択画面へ
	public String toStage(int userId,Model model) {
		//ステージ選択ボタンの表示をステータスによって変更したい
		//→HTMLファイルで分岐処理になるかと思うのでModelにuserIdから取得したUserTableEntityをつめてください
		return "";
	}

}
