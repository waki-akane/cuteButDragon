package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.MyMonsterDTO;
import com.example.demo.entity.ActionEntity;
import com.example.demo.entity.MyMonsterEntity;

public interface MyMonsterService {

	//指定IDのｍｍの表示
	public MyMonsterEntity showMm(int mmId);

	//指定IDのｍｍのレベルアップ時のステータス変更（ＨＰ・攻撃力・レベル）
	public int mmLevelUp(int mmId);

	//ｍｍの登録
	public void createMm(MyMonsterDTO myMonsterDTO);

	//指定IDのドラゴンの攻撃情報の取得
	public List<ActionEntity> mmAllAction(int mmId);
	
	//ステージクリア後の経験値アップ
	public void addEx(int emId,int mmId,boolean result);
		
	//攻撃受けた時のHP変動
	public int subHP(int currentHp,int actionId);


}
