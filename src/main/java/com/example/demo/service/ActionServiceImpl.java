package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ActionEntity;
import com.example.demo.entity.InitialMonsterEntity;
import com.example.demo.repository.ActionRepository;
import com.example.demo.repository.InitialMonsterRepository;

@Service
public class ActionServiceImpl implements ActionService {

	@Autowired
	ActionRepository actr;
	
	@Autowired
	InitialMonsterRepository imr;

	@Override
	public ActionEntity showAction(int actionId) {
		Optional<ActionEntity> op = actr.findById(actionId);
		return op.get();
	}
	
	// 指定IDのドラゴンの攻撃情報の取得
	@Override
	public List<ActionEntity> imAllAction(int imId) {
		InitialMonsterEntity im = imr.findById(imId).get();
		List<ActionEntity> list = new ArrayList<>();
		list.add(im.getImAction1());
		list.add(im.getImAction2());
		list.add(im.getImAction3());
		return list;
	}
}