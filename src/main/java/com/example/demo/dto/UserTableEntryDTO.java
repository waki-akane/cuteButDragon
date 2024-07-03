package com.example.demo.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class UserTableEntryDTO implements Serializable {
	@Size(max = 10, message = "1～10文字のひらがな")
	@NotEmpty(message = "名前を入力してください。")
	private String name;
	
	 @Pattern(regexp = "^[a-zA-Z0-9]{8,16}$", message = "半角英数字のみ使用可")
	 @Size(min=6,max = 16,message = "6～16文字")
	 @NotEmpty(message = "パスワードを入力してください。")
	private String pass;

}
