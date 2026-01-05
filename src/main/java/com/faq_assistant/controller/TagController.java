package com.faq_assistant.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faq_assistant.dto.ApiResponse;
import com.faq_assistant.entity.Tag;
import com.faq_assistant.service.TagService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tags")
public class TagController {

	private final TagService tagService;
	
	@PostMapping
	public ResponseEntity<ApiResponse<Tag>> create(@RequestBody Tag tag) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse<>(true, tagService.createTag(tag), "Tag created"));
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<Tag>>> getAll() {
		return ResponseEntity.ok(new ApiResponse<>(true, tagService.getAllTag(), "Tags fetched"));
	}
}
