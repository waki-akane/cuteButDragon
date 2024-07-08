package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dto.UserTableEntryDTO;
import com.example.demo.service.UsertableService;
import com.example.demo.service.userdetails.UserDetailsImpl;

public class UserController {
	
	@Autowired
	UsertableService uts;
	
	@GetMapping("/login")
	public String loginForm() {
		// ログイン画面を表示
		return "login";
	}
	
	@GetMapping("loginsuccess")
	public String loginSuccess(Model model) {
		// ユーザー名
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl principal = (UserDetailsImpl) auth.getPrincipal();
		model.addAttribute("username", principal.getUsername());
		// ログインに成功したら表示する URL
		return "stage";
		
	}
	
	// SecurityConfig の failureUrl で指定した URL と?のうしろのパラメータ
	@GetMapping(value="/login", params="failure")
	public String loginFail(Model model) {
		model.addAttribute("failureMessage", "ログインに失敗しました");
		// ログイン画面を表示
		return "login";
	}
	
	@GetMapping("addUser")
	public String addUser(UserTableEntryDTO ute) {
		uts.clearUser(ute);
		return "new";
	}
	
	
		
}
