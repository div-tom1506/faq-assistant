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
import com.faq_assistant.entity.Category;
import com.faq_assistant.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<ApiResponse<Category>> create(@RequestBody Category category) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse<>(true, categoryService.createCategory(category), "Category created"));
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<Category>>> getAll() {
		return ResponseEntity.ok(
				new ApiResponse<>(true, categoryService.getAllCategory(), "Category fetched")
				);
	}
}
