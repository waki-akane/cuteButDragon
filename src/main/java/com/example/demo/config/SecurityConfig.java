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
	
	//セッション機能の追加
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
	            //セッション機能の追加
	            .successHandler(successHandler)
	        )
	        .logout(logout -> logout
	            .logoutSuccessUrl("/login?logout") // ログアウト成功後のリダイレクト先 URL
	            .logoutUrl("/logout")
                .invalidateHttpSession(true) // セッション無効化
                .deleteCookies("JSESSIONID") // クッキー削除
	        )
	        .authorizeHttpRequests(authz -> authz
	            .requestMatchers("/login", "/user").permitAll() // 「/login」「/register」はすべて許可
	            .anyRequest().authenticated() // それ以外のリクエストは認証が必要
	        );
	    
	    return http.build();
	}

	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	 //System.out.println(new BCryptPasswordEncoder().encode());
	return new BCryptPasswordEncoder();
	
	}
	
	//セッション機能の追加
//	@Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
//    }


}
