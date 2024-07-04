package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.EnemyMonsterEntity;

public interface EnemyMonsterRepository extends JpaRepository<EnemyMonsterEntity, Integer> {

}
