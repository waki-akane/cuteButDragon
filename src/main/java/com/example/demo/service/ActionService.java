package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.ActionEntity;


public interface ActionService {
	
	//指定IDの攻撃の情報取得
	public ActionEntity showAction(int actionId);
	
	//指定IDのドラゴンの攻撃情報の取得
	public List<ActionEntity> imAllAction(int ImId);

}
