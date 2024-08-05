package com.example.demo.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserTableEntryDTO implements Serializable {
	@Size(max = 10, message = "最大文字数10文字を超えています。")
	@NotEmpty(message = "名前を入力してください。")
	@Pattern(regexp = "^[//p{InHiragana}ー]+$", message = "ひらがなで入力してください。")
	private String name;
	
	 @Pattern(regexp = "^[a-zA-Z0-9]{8,16}$", message = "半角英数字で入力してください。")
	 @Size(min=6,max = 16,message = "6～16文字で入力してください。")
	 @NotEmpty(message = "パスワードを入力してください。")
	 private String pass;

}
