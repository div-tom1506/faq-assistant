package com.faq_assistant.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AiSuggestionRequestDto {

	@NotBlank
	private String question;
}
