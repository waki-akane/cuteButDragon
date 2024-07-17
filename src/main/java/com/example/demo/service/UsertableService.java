package com.example.demo.service;

import com.example.demo.dto.UserTableEntryDTO;
import com.example.demo.entity.UserTableEntity;

public interface UsertableService {
	
	//新規登録
	public int createUser(UserTableEntryDTO userTableDTO);
	
	//ステージクリア時のステータス変更
	public void clearUser(int userId,int selectStage);
	
	//ユーザー情報の表示
	public UserTableEntity getByUserId(int userId);

}
