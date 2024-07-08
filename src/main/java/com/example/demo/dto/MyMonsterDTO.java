package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MyMonsterDTO {

	private Integer userId;

	@NotBlank(message = "画像を選択してください")
	private Integer imId;

	@NotBlank(message = "なまえを入力してください")
	@Size(min = 1, max = 10, message = "なまえは1から10文字の間で入力してください")
	@Pattern(regexp = "^[\\u3040-\\u309F]+$", message = "名前はひらがなで入力してください")
	private String name;
}
