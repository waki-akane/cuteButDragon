package com.example.demo.service.userdetails;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.UserTableEntity;

public class UserDetailsImpl implements UserDetails {
	
	private final UserTableEntity userTableEntity;

	private Collection<GrantedAuthority> authorities;
	
	public UserDetailsImpl(UserTableEntity userTableEntity) {
		this.userTableEntity = userTableEntity;
		//this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {

		return userTableEntity.getPass();
	}

	@Override
	public String getUsername() {
		
		return userTableEntity.getUserName();
	}
	
	@Override
	public boolean isAccountNonExpired() {
	// ユーザーが期限切れでなければ true を返す
	return true;
	}
	@Override
	public boolean isAccountNonLocked() {
	// ユーザーがロックされていなければ true を返す
	return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
	// ユーザーのパスワードの期限が切れていなければ true を返す
	return true;
	}
	@Override
	public boolean isEnabled() {
	// ユーザーが有効であれば true を返す
	return true;
	}



}
