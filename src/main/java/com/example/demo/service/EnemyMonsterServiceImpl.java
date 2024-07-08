package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EnemyMonsterEntity;
import com.example.demo.repository.ActionRepository;
import com.example.demo.repository.EnemyMonsterRepository;
import com.example.demo.repository.MyMonsterRepository;

@Service
public class EnemyMonsterServiceImpl implements EnemyMonsterService {

	@Autowired
	EnemyMonsterRepository emr;
	
	@Autowired
	ActionRepository actr;
	
	@Autowired
	MyMonsterRepository mmr;
	

	@Override
	public EnemyMonsterEntity showEm(int emId) {
		Optional<EnemyMonsterEntity> op = emr.findById(emId);
		return op.get();
	}

	@Override
	public int subEmHp(int currentEmHp, int actionId,int mmId) {
		currentEmHp = currentEmHp - (actr.findById(actionId).get().getAttack() + mmr.findById(mmId).get().getMmAttack());
		return currentEmHp;
	}
}
