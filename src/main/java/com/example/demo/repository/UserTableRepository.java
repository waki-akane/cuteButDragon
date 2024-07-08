package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.UserTableEntity;

public interface UserTableRepository extends JpaRepository<UserTableEntity, Integer>{

	@Query("SELECT * FROM public.usertable WHERE user_id=:userId ORDER BY id LIMIT 1")
	Optional<UserTableEntity> findByUserName(String userName);


	@Modifying
	@Query("INSERT INTO public.usertable (user_name,pass,user_id,status) VALUES (:user_name,:pass,:user_id,:status)")
	void insertUserTable(@Param("user_name") String userName,@Param("pass") String pass,@Param("user_id") String userId,@Param("status") Integer status);
	


}
