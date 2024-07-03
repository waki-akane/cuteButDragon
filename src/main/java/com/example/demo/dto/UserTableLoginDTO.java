package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserTableLoginDTO {
	@NotEmpty(message = "名前を入力してください。")
	private String name;
	
	@NotNull(message = "パスワードを入力してください。")
	private String pass;

}
