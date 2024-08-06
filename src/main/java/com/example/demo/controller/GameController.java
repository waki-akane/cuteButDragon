package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.ActionEntity;
import com.example.demo.entity.EnemyMonsterEntity;
import com.example.demo.entity.MyMonsterEntity;
import com.example.demo.entity.URL;
import com.example.demo.entity.UserTableEntity;
import com.example.demo.service.ActionService;
import com.example.demo.service.EnemyMonsterService;
import com.example.demo.service.InitialMonsterService;
import com.example.demo.service.MyMonsterService;
import com.example.demo.service.UsertableService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GameController {

	@Autowired
	MyMonsterService mms;

	@Autowired
	ActionService as;

	@Autowired
	EnemyMonsterService ems;

	@Autowired
	UsertableService uts;

	@Autowired
	InitialMonsterService ims;

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
	//　⑦逃げる選択後画面

	//battle1 -> Battle2(技選択画面)へ
	//①モンスター遭遇 -> ②攻撃選択
	@GetMapping("/next")
	public String toBattle2(HttpSession session, Model model, @RequestParam("selectStage") int selectStage) {
		
		//sessionからユーザー情報⇒MM情報の取得
		UserTableEntity user = (UserTableEntity) session.getAttribute("user");
		int userId = user.getUserId();
		MyMonsterEntity mm = mms.findByUserId(userId);
		model.addAttribute("mm", mm);

		List<ActionEntity> actionList = as.imAllAction(mm.getIm().getImId());
		//現在のアクションリスト（セッションに追加）
		session.setAttribute("actionList", actionList);
		model.addAttribute("actionList",actionList);
		
		//元のアクションリスト
		model.addAttribute("actions",actionList);
		
		//EM情報の取得
		EnemyMonsterEntity em = ems.showEm(selectStage);
		model.addAttribute("em", em);
		
		//各現在のHPをモデルへ
		currentMmHp = mm.getMmHp();
		model.addAttribute("currentMmHp", currentMmHp);
		currentEmHp = em.getEmHp();
		model.addAttribute("currentEmHp", currentEmHp);
		
		model.addAttribute("url", URL.url);

		return "battle/battle2";
	}

	//battle2→battle3
	@PostMapping("/battle/battle3")
	public String toBattle3(HttpSession session, Model model, @RequestParam("selectStage") int selectStage,
			@RequestParam("currentEmHp") int currentEmHp, @RequestParam("currentMmHp") int currentMmHp,
			@RequestParam("selectAction") int selectAction) {
		
		//ユーザー情報⇒MM情報の取得
		UserTableEntity user = (UserTableEntity) session.getAttribute("user");
		int userId = user.getUserId();
		MyMonsterEntity mm = mms.findByUserId(userId);
		model.addAttribute("mm", mm);
		
		//EM情報の取得
		EnemyMonsterEntity em = ems.showEm(selectStage);
		model.addAttribute("em", em);
		
		//現在のMMHp
		model.addAttribute("currentMmHp", currentMmHp);
		
		//現在のアクションリストから選択攻撃を取得
		ArrayList<ActionEntity> nowList = (ArrayList<ActionEntity>)session.getAttribute("actionList");
		ActionEntity action = nowList.get(selectAction);
		//攻撃力の取得
		int attack = action.getAttack() + mm.getMmAttack();
		model.addAttribute("attack", attack);
		//EMの残りHpの計算
		currentEmHp = currentEmHp - attack;
		if(currentEmHp < 0) {
			currentEmHp = 0;
		}
		model.addAttribute("currentEmHp", currentEmHp);
		//選択攻撃の技ポイントを減らす
		action.setTechPoint(action.getTechPoint() - 1);
		nowList.set(selectAction,action);
		//現在の技リストの更新
		session.setAttribute("actionList", nowList);
		model.addAttribute("actionList",nowList);
		model.addAttribute("action",action);
		
		model.addAttribute("url", URL.url);
		
		model.addAttribute("selectAction",selectAction);

		return "battle/battle3";
	}
	
	//[逃げる]ボタン押下時処理
	//battle2 -> battle7
	@GetMapping("/nigeru")
	public String nigeru(Model model) {
		model.addAttribute("url",URL.url);
		
		return "battle/battle7";
	}

	//battle3 -> battle4 or battle3 -> battle6
	@PostMapping("/battle/battle4")
	public String toBattle4(HttpSession session, Model model, @RequestParam("selectStage") int selectStage,
			@RequestParam("currentEmHp") int currentEmHp, @RequestParam("currentMmHp") int currentMmHp) {
		
		//User情報→MM情報の取得
		UserTableEntity user = (UserTableEntity) session.getAttribute("user");
		int userId = user.getUserId();
		MyMonsterEntity mm = mms.findByUserId(userId);
		model.addAttribute("mm", mm);
		
		//EM情報の取得
		EnemyMonsterEntity em = ems.showEm(selectStage);
		model.addAttribute("em", em);
		
		model.addAttribute("url", URL.url);
		
		//EMHpが０以下になった時 → battle5へ
		if (currentEmHp <= 0) {
			model.addAttribute("currentMmHp", currentMmHp);
			model.addAttribute("currentEmHp", currentEmHp);
			return "battle/battle5";
		}
		
		//EMの攻撃技をランダムに生成
		Random rand = new Random();
		int i = rand.nextInt(3);
		int emAttack = 0;
		model.addAttribute("emAttack",emAttack);
		if (i == 0) {
			emAttack = em.getEmAction1().getAttack();
			model.addAttribute("action",em.getEmAction1());
		} else if (i == 1) {
			emAttack = em.getEmAction2().getAttack();
			model.addAttribute("action",em.getEmAction1());
		} else if (i == 2) {
			emAttack = em.getEmAction3().getAttack();
			model.addAttribute("action",em.getEmAction1());
		}
		
		//MMHpの減少
		currentMmHp = currentMmHp - emAttack;
		if(currentMmHp < 0) {
			currentMmHp = 0;
		}

		model.addAttribute("currentMmHp", currentMmHp);
		model.addAttribute("currentEmHp", currentEmHp);
		
		return "battle/battle4";
	}

	//battle4 -> battle2 or battle4 -> battle5
	@PostMapping("/battle/battle2")
	public String toBattle2(HttpSession session, Model model, @RequestParam("selectStage") int selectStage,
			@RequestParam("currentEmHp") int currentEmHp, @RequestParam("currentMmHp") int currentMmHp) {
		
		//User情報→MM情報の取得
		UserTableEntity user = (UserTableEntity) session.getAttribute("user");
		int userId = user.getUserId();
		MyMonsterEntity mm = mms.findByUserId(userId);
		model.addAttribute("mm", mm);
		
		//現在のMM技リストをセッションから取得
		ArrayList<ActionEntity> nowList = (ArrayList<ActionEntity>)session.getAttribute("actionList");
		model.addAttribute("actionList", nowList);
		
		//EM情報の取得
		EnemyMonsterEntity em = ems.showEm(selectStage);
		model.addAttribute("em", em);
		
		model.addAttribute("url", URL.url);
		
		////MMHpが０以下になった時 → battle6へ
		if (currentMmHp <= 0) {
			model.addAttribute("currentMmHp", currentMmHp);
			model.addAttribute("currentEmHp", currentEmHp);
			return "battle/battle6";
		}

		model.addAttribute("currentMmHp", currentMmHp);
		model.addAttribute("currentEmHp", currentEmHp);
		
		//デフォルト時の技リストの取得
		List<ActionEntity> koteiList = as.imAllAction(mm.getIm().getImId());
		model.addAttribute("actions",koteiList);

		return "battle/battle2";
	}

	//battle6 -> result
	@PostMapping("/toLoseResult")
	public String lose(HttpSession session, Model model, @RequestParam("selectStage") int selectStage) {
		//User情報→MM情報の取得（BeforeMMとしてモデルに追加）
		UserTableEntity user = (UserTableEntity) session.getAttribute("user");
		int userId = user.getUserId();
		MyMonsterEntity mm = mms.findByUserId(userId);
		model.addAttribute("beforeMm", mm);
		
		//EM情報の取得
		EnemyMonsterEntity em = ems.showEm(selectStage);
		model.addAttribute("em", em);
		
		//セッションから現在の技リストを削除
		session.removeAttribute("actionList");
		
		//勝敗をBooleanに反映
		boolean result = false;
		//勝敗を元に経験値を追加
		mms.addEx(selectStage, mm.getMmId(), result);
		//ステータス変更後のMM情報を取得
		mm = mms.findByUserId(userId);
		
		//レベルアップしたか否かの変数
		boolean level = false;
		
		//Exバーを表示するための％を取得
		double i = 0;
		if(mm.getMmLevel() == 1) {
			if(mm.getMmId() > 1000 ) {
				i = 100;
			}else {
				i = mm.getMmEx() / 1000;
			}
			model.addAttribute("maxEx", 1000);
		}else if(mm.getMmLevel() == 2) {
			if(mm.getMmId() > 2000 ) {
				i = 100;
			}else {
				i = mm.getMmEx() / 2000;
			}
			model.addAttribute("maxEx", 2000);
		}else if(mm.getMmLevel() == 3) {
			if(mm.getMmId() > 3000 ) {
				i = 100;
			}else {
				i = mm.getMmEx() / 3000;
			}
			model.addAttribute("maxEx", 3000);
		}
		model.addAttribute("i",i);
		
		//MMの変更後ステータスを元にレベルアップ
		if (mm.getMmLevel() <= 2 && mm.getMmEx() >= Ex2) {
			//model.addAttribute("beforeMm", mm);
			mms.mmLevelUp(mm.getMmId());
			mm = mms.showMm(mm.getMmId());
			level = true;
		}
		if (mm.getMmLevel() <= 3 && mm.getMmEx() >= Ex3) {
			//model.addAttribute("beforeMm", mm);
			mms.mmLevelUp(mm.getMmId());
			mm = mms.showMm(mm.getMmId());
			level = true;
		}
		if (mm.getMmLevel() <= 4 && mm.getMmEx() >= Ex4) {
			//model.addAttribute("beforeMm", mm);
			mms.mmLevelUp(mm.getMmId());
			mm = mms.showMm(mm.getMmId());
			level = true;
		}

		model.addAttribute("mm", mm);
		model.addAttribute("level" ,level);
		model.addAttribute("result",result);

		model.addAttribute("url", URL.url);

		return "result";
	}

	//battle5 -> result
	@PostMapping("/toWinResult")
	public String win(HttpSession session, Model model, @RequestParam("selectStage") int selectStage) {
		
		//User情報→MM情報の取得（BeforeMMとしてモデルに追加）
		UserTableEntity user = (UserTableEntity) session.getAttribute("user");
		int userId = user.getUserId();
		MyMonsterEntity mm = mms.findByUserId(userId);
		model.addAttribute("beforeMm", mm);
		
		//セッションから現在の技リストを削除
		session.removeAttribute("actionList");
		
		//EM情報の取得
		EnemyMonsterEntity em = ems.showEm(selectStage);
		model.addAttribute("em", em);
		
		//勝敗をBooleanに反映
		boolean result = true;
		//勝敗を元に経験値を追加
		mms.addEx(selectStage, mm.getMmId(), result);
		//ステータス変更後のMM情報を取得
		mm = mms.findByUserId(userId);
		//レベルアップしたか否かを反映する変数
		boolean level = false;
		
		//Exバーを表示するための％を取得
		double i = 0;
		if(mm.getMmLevel() == 1) {
			if(mm.getMmId() > 1000 ) {
				i = 100;
			}else {
				i = mm.getMmEx() / 1000;
			}
			model.addAttribute("maxEx", 1000);
		}else if(mm.getMmLevel() == 2) {
			if(mm.getMmId() > 2000 ) {
				i = 100;
			}else {
				i = mm.getMmEx() / 2000;
			}
			model.addAttribute("maxEx", 2000);
		}else if(mm.getMmLevel() == 3) {
			if(mm.getMmId() > 3000 ) {
				i = 100;
			}else {
				i = mm.getMmEx() / 3000;
			}
			model.addAttribute("maxEx", 3000);
		}
		model.addAttribute("i",i);
		
		//MMの更新後ステータスを元にレベルアップ
		if (mm.getMmLevel() <= 2 && mm.getMmEx() >= Ex2) {
			//model.addAttribute("beforeMm",mm);
			mms.mmLevelUp(mm.getMmId());
			mm = mms.showMm(mm.getMmId());
			level = true;
		}

		if (mm.getMmLevel() <= 3 && mm.getMmEx() >= Ex3) {
			//model.addAttribute("beforeMm",mm);
			mms.mmLevelUp(mm.getMmId());
			mm = mms.showMm(mm.getMmId());
			level = true;
		}

		if (mm.getMmLevel() <= 4 && mm.getMmEx() >= Ex4) {
			//model.addAttribute("beforeMm", mm);
			mms.mmLevelUp(mm.getMmId());
			mm = mms.showMm(mm.getMmId());
			level = true;
		}

		
		model.addAttribute("level",level);
		model.addAttribute("result",result);
		
		model.addAttribute("url", URL.url);
		
		//ステージ３初回クリア時エンドロールへ、それ以外はステータスの変更
		if(user.getStatus() == 3 && selectStage == 3) {
			uts.clearUser(user.getUserId(), selectStage);
			return "endroll";
		}else if (user.getStatus() == selectStage){
			uts.clearUser(user.getUserId(), selectStage);
		}
		
		mm = mms.showMm(mm.getMmId());
		model.addAttribute("mm", mm);
		
		UserTableEntity ut = uts.getByUserId(userId);
		session.setAttribute("user", ut);
		

		return "result";
	}
	
	
	@GetMapping("/toRedirectStage")
	public String toStage(Model model, HttpSession session) {
		UserTableEntity user = (UserTableEntity) session.getAttribute("user");
		MyMonsterEntity mm = mms.findByUserId(user.getUserId());
		
		model.addAttribute("user", user);
		model.addAttribute("mm",mm);
		model.addAttribute("url",URL.url);
		
		int maxEx=0;
		if(mm.getMmLevel()==1) {
			maxEx=1000;
		}else if(mm.getMmLevel()==2) {
			maxEx=2000;
		}else if(mm.getMmLevel()==3) {
			maxEx=3000;
		}
		model.addAttribute("lv",maxEx);
		
		return "redirect:stage"; //stageページにリダイレクト
	}
	

}
