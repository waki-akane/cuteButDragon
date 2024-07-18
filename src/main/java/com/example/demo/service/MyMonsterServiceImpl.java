package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MyMonsterDTO;
<<<<<<< HEAD
=======
import com.example.demo.entity.ActionEntity;
>>>>>>> stash
import com.example.demo.entity.EnemyMonsterEntity;
import com.example.demo.entity.MyMonsterEntity;
import com.example.demo.entity.UserTableEntity;
import com.example.demo.repository.ActionRepository;
import com.example.demo.repository.EnemyMonsterRepository;
import com.example.demo.repository.InitialMonsterRepository;
import com.example.demo.repository.MyMonsterRepository;

@Service
public class MyMonsterServiceImpl implements MyMonsterService {

	@Autowired
	private MyMonsterRepository mmr;

	@Autowired
	private ActionRepository actr;

	@Autowired
	private InitialMonsterRepository imr;

	@Autowired
<<<<<<< HEAD
=======
	private UserTableRepository utr;
	
	@Autowired
>>>>>>> stash
	private EnemyMonsterRepository emr;

	// 指定IDのｍｍの表示
	@Override
	public MyMonsterEntity showMm(int mmId) {
		Optional<MyMonsterEntity> op = mmr.findById(mmId);
		return op.get();
	}

	// 指定IDのｍｍのレベルアップ時のステータス変更（ＨＰ・攻撃力・レベル）
	@Override
<<<<<<< HEAD
	public int mmLevelUp(int mmId) {
		MyMonsterEntity mm = mmr.findById(mmId).get();
		mm.setMmHp(mm.getMmHp() + 50);
		mm.setMmAttack(mm.getMmAttack() + 50);
		mm.setMmLevel(mm.getMmLevel() + 1);
		mmr.save(mm);
		return 0;
=======
	public String mmLevelUp(int mmId) {
		MyMonsterEntity mm = mmr.findById(mmId).get();
		mm.setMmHp(mm.getMmHp() + 50);
		mm.setMmAttack(mm.getMmAttack() + 50);
		mm.setMmLevel(mm.getMmLevel() + 1);
		mmr.save(mm);
		return "ome!";
>>>>>>> stash
	}

	// ｍｍの登録
	@Override
	public void createMm(MyMonsterDTO myMonsterDTO, UserTableEntity user) {
		MyMonsterEntity mm = new MyMonsterEntity();
		// DTOの内容をエンティティに設定
		mm.setMmName(myMonsterDTO.getName());
		mm.setUserId(user);
		mm.setImId(imr.findById(myMonsterDTO.getImId()).get());
		mmr.save(mm);
	}


	//ステージクリア後の経験値アップ
	@Override
	public void addEx(int emId, int mmId, boolean result) {
		EnemyMonsterEntity em = emr.findById(emId).get();
		MyMonsterEntity mm = mmr.findById(mmId).get();
		if (result) {
			mm.setMmEx(mm.getMmEx() + em.getWin());
		} else {
			mm.setMmEx(mm.getMmEx() + em.getLose());
		}
		mmr.save(mm);
	}
<<<<<<< HEAD

	@Override
	public int subHP(int currentHp, int actionId) {
		currentHp = currentHp - actr.findById(actionId).get().getAttack();
		return currentHp;
	}

	@Override
	public MyMonsterEntity findByUserId(int userId) {
		MyMonsterEntity mm = mmr.findByUserId(userId);
		return mm;
	}

}
=======
	
	//ステージクリア後の経験値アップ
	@Override
	public void addEx(int emId, int mmId,boolean result) {
		EnemyMonsterEntity em = emr.findById(emId).get();
		MyMonsterEntity mm = mmr.findById(mmId).get();
		if(result) {
			mm.setMmEx(mm.getMmEx() + em.getWin());
		}else {
			mm.setMmEx(mm.getMmEx() + em.getLose());
		}
		mmr.save(mm);
	}

	@Override
	public int subHP(int currentHp, int actionId) {
		currentHp = currentHp - actr.findById(actionId).get().getAttack();
		return currentHp;
	}
	
	
	
}
>>>>>>> stash
