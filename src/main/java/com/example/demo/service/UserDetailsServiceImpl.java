package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.repository.UserTableRepository;
import com.example.demo.service.userdetails.UserDetailsImpl;

public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserTableRepository userTableRepository;

	@Override
	public UserDetails loadUserByUserId(Integer userId) throws UsernameNotFoundException {
		
		//Optional<UserTableEntity>loginUser = userTableRepository.findById(pass);
		//Collection<GrantedAuthority> authorities = new ArrayList<>();
		 try {
	            String sql = "SELECT * FROM usertable WHERE user_name = ?";
	            Map<String, Object> map = .queryForMap(sql, username);
	            String password = (String)map.get("pass");
	            Collection<GrantedAuthority> authorities = new ArrayList<>();
	            authorities.add(new SimpleGrantedAuthority((String)map.get("authority")));
	            return new UserDetailsImpl(username, password, authorities);
	        } catch (Exception e) {
	            throw new UsernameNotFoundException("ユーザーが見つかりません", e);
	        }
	}

}
