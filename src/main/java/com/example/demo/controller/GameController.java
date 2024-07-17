package com.example.demo.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.ActionEntity;
import com.example.demo.entity.EnemyMonsterEntity;
import com.example.demo.entity.MyMonsterEntity;
import com.example.demo.service.ActionService;
import com.example.demo.service.EnemyMonsterService;
import com.example.demo.service.MyMonsterService;
import com.example.demo.service.userdetails.UserDetailsImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class GameController {
	
	@Autowired
	MyMonsterService mms;
	
	@Autowired
	ActionService as;
	
	@Autowired
	EnemyMonsterService ems;
	
	int currentEmHp;
	
	int currentMmHp;
	
	int Ex2 = 1000;
	int Ex3 = 2000;
	int Ex4 = 3000;
	
//	①モンスター遭遇
//	②攻撃選択
//	③味方モンスターの攻撃、相手へのダメージ
//	④敵モンスターの攻撃、味方へのダメージ
//	⑤敵モンスターHP０
//	⑥味方モンスターHP０
	
	//battle1 -> Battle2(技選択画面)へ
	@GetMapping("/next")
    public String toBattle2(HttpSession session, Model model,int selectStage) {
		UserDetailsImpl user = (UserDetailsImpl) session.getAttribute("user");
		int userId = user.getUserId();
		
		MyMonsterEntity mm = mms.findByUserId(userId);
		model.addAttribute("mm",mm);
		
		List<ActionEntity> actionList = as.imAllAction(mm.getImId().getImId());
		model.addAttribute("actionList", actionList);
		
		EnemyMonsterEntity em = ems.showEm(userId);
		model.addAttribute("em",em);
		
		currentMmHp = mm.getMmHp();
		model.addAttribute("currentMmHp",currentMmHp);
		currentEmHp = em.getEmHp();
		model.addAttribute("currentEmHp",currentEmHp);
		
		
		return "battle/battle2";
	}
	
	//battle2→battle3
	@GetMapping("/battle/battle3")
	public String toBattle3(HttpSession session,Model model,int selectStage,int currentEmHp,int currentMmHp,int selectAction) {
		UserDetailsImpl user = (UserDetailsImpl) session.getAttribute("user");
		int userId = user.getUserId();
		
		MyMonsterEntity mm = mms.findByUserId(userId);
		model.addAttribute("mm",mm);
		
		EnemyMonsterEntity em = ems.showEm(selectStage);
		model.addAttribute("em",em);
		
		model.addAttribute("currentMmHp",currentMmHp);
		model.addAttribute("currentEmHp",currentEmHp);
		
		ActionEntity action = as.showAction(selectAction);
		model.addAttribute(action);
		
		return "battle/battle3";
	}
	
	//battle3 -> battle4 or battle3 -> battle6
	@GetMapping("/battle/battle4")
	public String toBattle4(HttpSession session,Model model,int selectStage,int currentEmHp,int currentMmHp,int selectAction) {
		UserDetailsImpl user = (UserDetailsImpl) session.getAttribute("user");
		int userId = user.getUserId();
		
		MyMonsterEntity mm = mms.findByUserId(userId);
		model.addAttribute("mm",mm);
		
		EnemyMonsterEntity em = ems.showEm(selectStage);
		model.addAttribute("em",em);
		
		currentEmHp = currentEmHp - (as.showAction(selectAction).getAttack() + mm.getMmAttack());
		
		if(currentEmHp <= 0) {
			return "battle/battle6";
		}
		
		model.addAttribute("currentMmHp",currentMmHp);
		model.addAttribute("currentEmHp",currentEmHp);
		
		return "battle/battle4";
	}
	
	//battle4 -> battle2 or battle4 -> battle5
	@GetMapping("/battle/battle2")
	public String toBattle2(HttpSession session,Model model,int selectStage,int currentMmHp,int currentEmHp) {
		UserDetailsImpl user = (UserDetailsImpl) session.getAttribute("user");
		int userId = user.getUserId();
		
		MyMonsterEntity mm = mms.findByUserId(userId);
		model.addAttribute("mm",mm);
		
		List<ActionEntity> actionList = as.imAllAction(mm.getImId().getImId());
		model.addAttribute("actionList", actionList);
		
		EnemyMonsterEntity em = ems.showEm(selectStage);
		model.addAttribute("em",em);
		
		Random rand = new Random();
		int i = rand.nextInt(3);
		int emAttack = 0;
		if(i == 0) {
			emAttack = em.getEmAction1().getAttack();
		}else if(i == 1) {
			emAttack = em.getEmAction2().getAttack();
		}else if(i == 2) {
			emAttack = em.getEmAction3().getAttack();
		}
		
		currentMmHp = currentMmHp - emAttack;
		
		if(currentMmHp <= 0) {
			return "battle/battle5";
		}
		
		model.addAttribute("currentMmHp",currentMmHp);
		model.addAttribute("currentEmHp",currentEmHp);
		
		return "battle/battle2";
	}
	
	//battle5 -> result
	@GetMapping("/toLoseResult")
	public String lose(HttpSession session,Model model,int selectStage) {
		UserDetailsImpl user = (UserDetailsImpl) session.getAttribute("user");
		int userId = user.getUserId();
		
		MyMonsterEntity mm = mms.findByUserId(userId);
		model.addAttribute("beforeMmEx",mm.getMmEx());
		
		EnemyMonsterEntity em = ems.showEm(selectStage);
		model.addAttribute("em",em);
		
		boolean result = false;
		mms.addEx(selectStage, mm.getMmId(), result);
		mm = mms.findByUserId(userId);
		
		//これだと連続レベルアップしたときにbeforeMmに入っているデータがおかしくなるんよなー
		if(mm.getMmLevel() <= 2 && mm.getMmEx() >= Ex2) {
			model.addAttribute("beforeMm",mm);
			mms.mmLevelUp(mm.getMmId());
			mm = mms.showMm(mm.getMmId());
		}
		
		if(mm.getMmLevel() <= 3 && mm.getMmEx() >= Ex3) {
			model.addAttribute("beforeMm",mm);
			mms.mmLevelUp(mm.getMmId());
			mm = mms.showMm(mm.getMmId());
		}
		
		if(mm.getMmLevel() <= 4 && mm.getMmEx() >= Ex4) {
			model.addAttribute("beforeMm",mm);
			mms.mmLevelUp(mm.getMmId());
			mm = mms.showMm(mm.getMmId());
		}
		
		model.addAttribute("mm", mm);
		
		return "result";
	}
	
	//battle6 -> result
		@GetMapping("/toWinResult")
		public String win(HttpSession session,Model model,int selectStage) {
			UserDetailsImpl user = (UserDetailsImpl) session.getAttribute("user");
			int userId = user.getUserId();
			
			MyMonsterEntity mm = mms.findByUserId(userId);
			model.addAttribute("beforeMmEx",mm.getMmEx());
			
			EnemyMonsterEntity em = ems.showEm(selectStage);
			model.addAttribute("em",em);
			
			boolean result = true;
			mms.addEx(selectStage, mm.getMmId(), result);
			mm = mms.findByUserId(userId);
			
			//これだと連続レベルアップしたときにbeforeMmに入っているデータがおかしくなるんよなー
			if(mm.getMmLevel() <= 2 && mm.getMmEx() >= Ex2) {
				model.addAttribute("beforeMm",mm);
				mms.mmLevelUp(mm.getMmId());
				mm = mms.showMm(mm.getMmId());
			}
			
			if(mm.getMmLevel() <= 3 && mm.getMmEx() >= Ex3) {
				model.addAttribute("beforeMm",mm);
				mms.mmLevelUp(mm.getMmId());
				mm = mms.showMm(mm.getMmId());
			}
			
			if(mm.getMmLevel() <= 4 && mm.getMmEx() >= Ex4) {
				model.addAttribute("beforeMm",mm);
				mms.mmLevelUp(mm.getMmId());
				mm = mms.showMm(mm.getMmId());
			}
			
			model.addAttribute("mm", mm);
			
			return "result";
		}
	

}
