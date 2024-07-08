package com.example.demo.service;


import com.example.demo.entity.EnemyMonsterEntity;

public interface EnemyMonsterService {
	
	
	//emの情報表示
	public EnemyMonsterEntity showEm(int emId);
	
	//ドラゴン攻撃時のHP減少
	public int subEmHp(int currentEmHp,int actionId,int mmId);



}
