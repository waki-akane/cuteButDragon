package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.entity.UserTableEntity;
import com.example.demo.repository.UserTableRepository;
import com.example.demo.repository.UserTableRoleRepository;
import com.example.demo.service.userdetails.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserTableRepository userTableRepository;
	@Autowired
	UserTableRoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<UserTableEntity>loginUser = userTableRepository.findByUserName(userName);
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		if (loginUser.isPresent()) {
			// ログインユーザーが存在するとき
			// 権限データを取得
			Iterable<Role> roleIte = roleRepository.findAll();
			Integer roleId = loginUser.get().getRoleId();
			for (Role role : roleIte) {
				if (roleId == role.getId()) {
					// ユーザーの権限に対応する権限名を設定する
					// "ROLE_◯◯"の名前で設定すると hasRole の権限になる
					// "ROLE_"をつけない場合は hasAuthority の権限になる
					authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
				}
			}
		} else {
			throw new UsernameNotFoundException("ユーザーが存在しません");
		}
		return new UserDetailsImpl(loginUser.get());
	
	}

	

	
	

}
