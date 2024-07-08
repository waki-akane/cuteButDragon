package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(form -> form
				.loginPage("/login") // ログイン画面の URL
				.loginProcessingUrl("/authenticate")// ユーザー名・パスワードの送信先 URL
				.defaultSuccessUrl("/loginsuccess") // ログイン成功後のリダイレクト先 URL
				.failureUrl("/login?failure") // ログイン失敗後のリダイレクト先 URL
				.permitAll() // ログイン画面は未ログインでもアクセス可能
				).logout(logout -> logout
						.logoutSuccessUrl("/login?logout") // ログアウト成功後のリダイレクト先 URL
						).authorizeHttpRequests(authz -> authz
								.requestMatchers("/login").permitAll()// 「/login」はすべて許可
								
								);
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	 //System.out.println(new BCryptPasswordEncoder().encode());
	return new BCryptPasswordEncoder();
	}

}
