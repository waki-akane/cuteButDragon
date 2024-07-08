package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ActionEntity;

public interface ActionRepository extends JpaRepository<ActionEntity, Integer> {
	List<ActionEntity> findByMmId(int mmId);

}
