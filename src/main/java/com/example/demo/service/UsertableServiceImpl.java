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
		ute.setPass(pass);
		utr.save(ute);
		return 0;
	}

	@Override
	public void clearUser(int UserId,int emId) {
		UserTableEntity ute = utr.findById(UserId).get();
		ute.setStatus(emId);
		utr.save(ute);
	}

	@Override
	public UserTableEntity showUser(int userId) {
		return utr.findById(userId).get();
	}


}
