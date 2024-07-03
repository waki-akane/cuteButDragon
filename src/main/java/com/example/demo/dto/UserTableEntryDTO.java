package com.example.demo.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTableEntryDTO implements Serializable {
	@Size(max = 10, message = "1～10文字のひらがな")
	private String name;
	
	 @Pattern(regexp = "^[a-zA-Z0-9]{8,16}$", message = "8～16文字の半角英数字")
	private String pass;

}
