//不要、HrsControllerへ

package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.UserTableEntity;

import jakarta.servlet.http.HttpSession;

@Controller
public class SampleController {

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
	
	@GetMapping("/home")
    public String home(Model model, HttpSession session) {
		UserTableEntity user = (UserTableEntity)session.getAttribute("user");
		int status = user.getStatus();
        model.addAttribute("status", status);
        return "home";
    }
	
	}
//
//}



