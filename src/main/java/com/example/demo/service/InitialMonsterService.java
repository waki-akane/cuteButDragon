package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.InitialMonsterEntity;

public interface InitialMonsterService {
	
	//全im情報の取得
	public List<InitialMonsterEntity> showAllIm();
	
	//指定IDのmmの情報取得
	public InitialMonsterEntity showIm(int imId);

}
