package com.example.demo.service;

import com.example.demo.dto.MyMonsterDTO;
import com.example.demo.entity.MyMonsterEntity;
import com.example.demo.entity.UserTableEntity;

public interface MyMonsterService {

	//指定IDのｍｍの表示
	public MyMonsterEntity showMm(int mmId);

	//指定IDのｍｍのレベルアップ時のステータス変更（ＨＰ・攻撃力・レベル）
	public int mmLevelUp(int mmId);

	//ｍｍの登録
	public void createMm(MyMonsterDTO myMonsterDTO,UserTableEntity user);


	//ステージクリア後の経験値アップ
	public void addEx(int emId, int mmId, Boolean result);

	//攻撃受けた時のHP変動
	public int subHP(int currentHp, int actionId);

	//指定ユーザーIDが所持するMM情報の取得
	public MyMonsterEntity findByUserId(int userId);

}
