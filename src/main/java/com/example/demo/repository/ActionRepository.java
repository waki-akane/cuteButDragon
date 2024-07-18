package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ActionEntity;

public interface ActionRepository extends JpaRepository<ActionEntity, Integer> {

}
