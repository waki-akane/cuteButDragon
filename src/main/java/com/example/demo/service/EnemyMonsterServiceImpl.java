package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EnemyMonsterEntity;
import com.example.demo.repository.EnemyMonsterRepository;

@Service
public class EnemyMonsterServiceImpl implements EnemyMonsterService {

	@Autowired
	EnemyMonsterRepository emr;

	@Override
	public EnemyMonsterEntity showEm(int emId) {
		Optional<EnemyMonsterEntity> op = emr.findById(emId);
		return op.get();
	}
}
