package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.InitialMonsterEntity;
import com.example.demo.repository.InitialMonsterRepository;

@Service
public class InitialMonsterServiceImpl implements InitialMonsterService{
	
	@Autowired
	InitialMonsterRepository imr;

	@Override
	public List<InitialMonsterEntity> showAllIm() {
		List<InitialMonsterEntity> list = imr.findAll();
		return list;
	}

	@Override
	public InitialMonsterEntity showIm(int imId) {
		Optional<InitialMonsterEntity> op = imr.findById(imId);
		InitialMonsterEntity im = op.get();
		return im;
	}


}
