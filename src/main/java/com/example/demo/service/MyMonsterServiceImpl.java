package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MyMonsterDTO;
import com.example.demo.entity.EnemyMonsterEntity;
import com.example.demo.entity.MyMonsterEntity;
import com.example.demo.entity.UserTableEntity;
import com.example.demo.repository.ActionRepository;
import com.example.demo.repository.EnemyMonsterRepository;
import com.example.demo.repository.InitialMonsterRepository;
import com.example.demo.repository.MyMonsterRepository;
import com.example.demo.repository.UserTableRepository;

@Service
public class MyMonsterServiceImpl implements MyMonsterService {

	@Autowired
	private MyMonsterRepository mmr;

	@Autowired
	private ActionRepository actr;

	@Autowired
	private InitialMonsterRepository imr;

	@Autowired
	private EnemyMonsterRepository emr;
	
	@Autowired
	private UserTableRepository utr;

	// 指定IDのｍｍの表示
	@Override
	public MyMonsterEntity showMm(int mmId) {
		Optional<MyMonsterEntity> op = mmr.findById(mmId);
		return op.get();
	}

	// 指定IDのｍｍのレベルアップ時のステータス変更（ＨＰ・攻撃力・レベル）
	@Override
	public int mmLevelUp(int mmId) {
		MyMonsterEntity mm = mmr.findById(mmId).get();
		mm.setMmHp(mm.getMmHp() + 100);
		mm.setMmAttack(mm.getMmAttack() + 100);
		mm.setMmLevel(mm.getMmLevel() + 1);
		mmr.save(mm);
		return 0;
	}

	// ｍｍの登録
	@Override
	public void createMm(MyMonsterDTO myMonsterDTO, UserTableEntity user) {
		MyMonsterEntity mm = new MyMonsterEntity();
		// DTOの内容をエンティティに設定
		mm.setMmName(myMonsterDTO.getName());
		mm.setUserId(user);
		mm.setIm(imr.findById(myMonsterDTO.getImId()).get());
		mm.setMmHp(300);
		mm.setMmLevel(1);
		mm.setMmEx(0);
		mm.setMmAttack(0);
		mmr.save(mm);
	}


	//ステージクリア後の経験値アップ
	@Override
	public void addEx(int emId, int mmId, Boolean result) {
		EnemyMonsterEntity em = emr.findById(emId).get();
		MyMonsterEntity mm = mmr.findById(mmId).get();
		if (result == true) {
			mm.setMmEx(mm.getMmEx() + em.getWin());
		} else if(result == false){
			mm.setMmEx(mm.getMmEx() + em.getLose());
		}
		mmr.save(mm);
	}

	@Override
	public int subHP(int currentHp, int actionId) {
		currentHp = currentHp - actr.findById(actionId).get().getAttack();
		return currentHp;
	}

	@Override
	public MyMonsterEntity findByUserId(int userId) {
		UserTableEntity user = utr.findById(userId).get();
		MyMonsterEntity mm = mmr.findByUserId(user);
		return mm;
	}

}