package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserTableEntity;

public interface UserTableRepository extends JpaRepository<UserTableEntity, Integer>{

	Optional<UserTableEntity> findByUserName(String userName);
	
	// UserTableEntity findByUserId(Integer userId);


}
