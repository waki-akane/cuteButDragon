package com.example.demo.service;

import com.example.demo.dto.UserTableDTO;
import com.example.demo.entity.UserTableEntity;

public interface UsertableService {
	
	//新規登録
	public int createUser(UserTableDTO userTableDTO);
	
	//ステージクリア時のステータス変更
	public int clearUser(UserTableDTO usreTableDTO);
	
	//ユーザー情報の表示
	public UserTableEntity showUser();

}
