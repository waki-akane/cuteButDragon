package com.example.demo.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTableLoginDTO {
	@Size(max = 10, message = "1～10文字のひらがなを入力してください")
	private String name;
	
	 @Pattern(regexp = "^[a-zA-Z0-9]{8,16}$", message = "8～16文字の半角英数字を入力してください")
	private String pass;

}
