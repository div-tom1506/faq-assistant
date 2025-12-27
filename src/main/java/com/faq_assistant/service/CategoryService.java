package com.faq_assistant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.faq_assistant.entity.Category;
import com.faq_assistant.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;
	
	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}
	
	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}
}
