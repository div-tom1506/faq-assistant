package com.faq_assistant.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FaqRequestDto {

	@NotBlank
	private String question;
	private String answer;
	@NotNull
	private Long categoryId;
	private List<Long> tagIds;
	@NotNull
	private Long userId;
	
}
