package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		UserTableEntity ute = new UserTableEntity();
		ute.setUserName(userTableDTO.getName());
		String pass = new BCryptPasswordEncoder().encode(userTableDTO.getPass());
		System.out.println(pass); 
		ute.setPass(pass);
		ute.setStatus(1);
		ute.setRoleId(1);
		utr.save(ute);
		return 0;
	}

	@Override
	public void clearUser(int userId, int selectStage) {
		UserTableEntity ute = utr.findById(userId).get();
		ute.setStatus(selectStage + 1);
		utr.save(ute);
	}

	@Override
	public UserTableEntity getByUserId(int userId) {
		return utr.findById(userId).get();
	}


}
