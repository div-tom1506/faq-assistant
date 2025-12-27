package com.faq_assistant.dto;

import java.time.LocalDateTime;
import java.util.List;

public class FaqResponseDto {

	private Long id;
	private String question;
	private String answer;
	private String category;
	private List<Long> tag;
	private String createdBy;
	private LocalDateTime createdAt;
}
