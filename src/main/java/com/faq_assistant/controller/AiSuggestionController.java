package com.faq_assistant.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faq_assistant.dto.AiSuggestionRequestDto;
import com.faq_assistant.dto.ApiResponse;
import com.faq_assistant.service.AiSuggestionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ai")
public class AiSuggestionController {
	
	private final AiSuggestionService aiSuggestionService;
	
	@PostMapping("/suggest-answer")
	public ResponseEntity<ApiResponse<Map<String, String>>> suggestAnswer(
			@Valid @RequestBody AiSuggestionRequestDto request) 
	{
		String answer = aiSuggestionService.suggestAnswer(request.getQuestion());
		
		return  ResponseEntity.ok(
				new ApiResponse<>(
						true,
						Map.of("suggestedAnswer", answer),
						"AI answer generated successfully"
				)
		);
	}

}
