package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ActionEntity;
import com.example.demo.repository.ActionRepository;

@Service
public class ActionServiceImpl implements ActionService {

	@Autowired
	ActionRepository actr;

	@Override
	public ActionEntity showAction(int actionId) {
		Optional<ActionEntity> op = actr.findById(actionId);
		return op.get();
	}
	
	// 指定IDのドラゴンの攻撃情報の取得
	@Override
	public List<ActionEntity> imAllAction(int mmId) {
		return actr.findByImId(mmId);
	}
}