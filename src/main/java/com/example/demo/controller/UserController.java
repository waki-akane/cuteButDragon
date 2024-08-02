package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.UserTableEntryDTO;
import com.example.demo.entity.MyMonsterEntity;
import com.example.demo.entity.URL;
import com.example.demo.service.MyMonsterService;
import com.example.demo.service.UserDetailsServiceImpl;
import com.example.demo.service.UsertableService;
import com.example.demo.service.userdetails.UserDetailsImpl;

@Controller
public class UserController {

	@Autowired
	UsertableService uts;
	
	@Autowired
	MyMonsterService mms;
	
	@Autowired
	UserDetailsServiceImpl udservice;
	
	@ModelAttribute
	public UserTableEntryDTO setUpForm() {
		return new UserTableEntryDTO();
	}

	@GetMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("url", URL.url);
		// ログイン画面を表示
		return "login";
	}

	@GetMapping("loginsuccess")
	public String loginSuccess(Model model) {
		// ユーザー名
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl principal = (UserDetailsImpl) auth.getPrincipal();
		model.addAttribute("username", principal.getUsername());
		model.addAttribute("url", URL.url);
		
		//ログインしてるユーザー情報の取得。モデルに追加
		model.addAttribute("user",principal.getUser());
		MyMonsterEntity mm = mms.findByUserId(principal.getUserId());
		model.addAttribute("mm",mm);
		// ログインに成功したら表示する URL
		
		return "stage";

	}

	// SecurityConfig の failureUrl で指定した URL と?のうしろのパラメータ
	@GetMapping(value = "/login", params = "failure")
	public String loginFail(Model model) {
		model.addAttribute("failureMessage", "ログインに失敗しました");
		model.addAttribute("url", URL.url);
		// ログイン画面を表示
		return "login";
	}

	@PostMapping("/user")
	public String addUser(@Validated UserTableEntryDTO ute, BindingResult br, Model model) {
		if (br.hasErrors()) {
			model.addAttribute("user", ute);
			model.addAttribute("url", URL.url);
			System.out.println(br.getAllErrors());
			return "user"; 
		}
		uts.createUser(ute);
		UserDetails udi = udservice.loadUserByUsername(ute.getName());
		//
		// 認証情報を作成
        Authentication auth = new UsernamePasswordAuthenticationToken(udi, null, udi.getAuthorities());

        // SecurityContextHolderに認証情報を設定
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(auth);
        SecurityContextHolder.setContext(securityContext);
        
     // ユーザー名
     	UserDetailsImpl principal = (UserDetailsImpl) auth.getPrincipal();
     	model.addAttribute("username", principal.getUsername());
     	model.addAttribute("password", ute.getPass());
        
		System.out.println(principal.getUsername());
		System.out.println(ute.getPass());
		model.addAttribute("url", URL.url);
		 //return "redirect:/loginsuccess";
		return "story";
	}

	@GetMapping("/user")
	public String showForm(Model model) {
		model.addAttribute("user", new UserTableEntryDTO());
		model.addAttribute("url", URL.url);
		return "user";
	}
	
}
