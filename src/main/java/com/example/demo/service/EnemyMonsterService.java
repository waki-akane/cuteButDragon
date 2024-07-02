package com.example.demo.service;


import com.example.demo.entity.EnemyMonsterEntity;
import com.example.demo.repository.EnemyMonsterRepository;

public interface EnemyMonsterService {
	
	public static final EnemyMonsterRepository emr = new EnemyMonsterRepository();
	
	public EnemyMonsterEntity showEm(int emId);

}
