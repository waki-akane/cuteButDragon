package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.UserTableEntryDTO;
import com.example.demo.service.UsertableService;
import com.example.demo.service.userdetails.UserDetailsImpl;

@Controller
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
	
	@PostMapping("/register")
	public String addUser(UserTableEntryDTO ute,Model model) {
		uts.createUser(ute);
		model.addAttribute("user", new UserTableEntryDTO()); 
		return "redirect:login";
	}
	
	@GetMapping("/register")
	public String showForm(Model model) {
		model.addAttribute("user",new UserTableEntryDTO());
		return "register";
	}
	
	
		
}
