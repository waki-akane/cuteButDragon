package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
=======
>>>>>>> stash
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserTableEntryDTO;
import com.example.demo.entity.UserTableEntity;
import com.example.demo.repository.UserTableRepository;

@Service
public class UsertableServiceImpl implements UsertableService{
	
	@Autowired
	UserTableRepository utr;

	@Override
	public int createUser(UserTableEntryDTO userTableDTO) {
<<<<<<< HEAD
		UserTableEntity ute = new UserTableEntity();
		ute.setUserName(userTableDTO.getName());
		String pass = new BCryptPasswordEncoder().encode(userTableDTO.getPass());
		System.out.println(pass); 
		ute.setPass(pass);
		utr.save(ute);
=======
		
>>>>>>> stash
		return 0;
	}

	@Override
	public void clearUser(int userId, int selectStage) {
		UserTableEntity ute = utr.findById(userId).get();
		ute.setStatus(selectStage);
		utr.save(ute);
	}

	@Override
<<<<<<< HEAD
	public UserTableEntity getByUserId(int userId) {
=======
	public UserTableEntity showUser(int userId) {
>>>>>>> stash
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public int getMaxId() {
		return utr.findMaxUserId();
	}


}
