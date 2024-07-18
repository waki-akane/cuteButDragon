package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.MyMonsterEntity;
import com.example.demo.entity.UserTableEntity;

public interface MyMonsterRepository extends JpaRepository<MyMonsterEntity, Integer> {
	MyMonsterEntity findByUserId(UserTableEntity user);

}
