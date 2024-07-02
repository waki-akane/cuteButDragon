package com.example.demo.service;

import com.example.demo.entity.EnemyExEntity;

public interface EnemyExService {
	
	//指定IDの敵モンスターの経験値情報取得
	public EnemyExEntity showEnemyEx(int enemyId);

}
