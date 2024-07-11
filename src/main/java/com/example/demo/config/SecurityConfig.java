package com.example.demo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.service.CustomAuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
    private CustomAuthenticationSuccessHandler successHandler;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http.formLogin(form -> form
	            .loginPage("/login") // ログイン画面の URL
	            .loginProcessingUrl("/authenticate") // ユーザー名・パスワードの送信先 URL
	            .defaultSuccessUrl("/loginsuccess") // ログイン成功後のリダイレクト先 URL
	            .failureUrl("/login?failure") // ログイン失敗後のリダイレクト先 URL
	            .permitAll() // ログイン画面は未ログインでもアクセス可能
	            .successHandler(successHandler)
	        )
	        .logout(logout -> logout
	            .logoutSuccessUrl("/login?logout") // ログアウト成功後のリダイレクト先 URL
	        )
	        .authorizeHttpRequests(authz -> authz
	            .requestMatchers("/login", "/register").permitAll() // 「/login」「/register」はすべて許可
	            .anyRequest().authenticated() // それ以外のリクエストは認証が必要
	        );
	    
	    return http.build();
	}

	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	 //System.out.println(new BCryptPasswordEncoder().encode());
	return new BCryptPasswordEncoder();
	
	}
	
//	@Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
//    }


}
