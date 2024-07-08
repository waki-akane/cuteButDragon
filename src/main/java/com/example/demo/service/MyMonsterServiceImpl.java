package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MyMonsterDTO;
import com.example.demo.entity.ActionEntity;
import com.example.demo.entity.MyMonsterEntity;
import com.example.demo.repository.ActionRepository;
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
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	// ｍｍの登録
	@Override
	public void createMm(MyMonsterDTO myMonsterDTO) {
		MyMonsterEntity mm = new MyMonsterEntity();
		// DTOの内容をエンティティに設定
		mm.setMmName(myMonsterDTO.getName());
		mm.setUserId(utr.findById(myMonsterDTO.getUserId()).get());
		mm.setImId(imr.findById(myMonsterDTO.getImId()).get());
		mmr.save(mm);
	}

	// 指定IDのドラゴンの攻撃情報の取得
	@Override
	public List<ActionEntity> mmAllAction(int mmId) {
		return actr.findByMmId(mmId);
	}
}
