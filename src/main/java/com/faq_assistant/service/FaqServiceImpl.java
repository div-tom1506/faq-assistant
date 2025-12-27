package com.faq_assistant.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.faq_assistant.dto.FaqRequestDto;
import com.faq_assistant.dto.FaqResponseDto;
import com.faq_assistant.entity.Category;
import com.faq_assistant.entity.Users;
import com.faq_assistant.repository.CategoryRepository;
import com.faq_assistant.repository.FaqRepository;
import com.faq_assistant.repository.TagRepository;
import com.faq_assistant.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
//for constructor injection
@RequiredArgsConstructor  
@Transactional
public class FaqServiceImpl implements FaqService{
	
	private FaqRepository faqRepository;
	private CategoryRepository categoryRepository;
	private TagRepository tagRepository;
	private UserRepository userRepository;

	@Override
	public FaqResponseDto createFaq(FaqRequestDto request) {
		
		Category category = categoryRepository.findById(request.getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException("Category not found"));
		Users users = userRepository.findById(request.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		
		
	}

	@Override
	public FaqResponseDto getFaqById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<FaqResponseDto> searchFaqs(String search, Long categoryId, List<Long> tagids, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FaqResponseDto updateFaq(Long id, FaqRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteFaq(Long id) {
		// TODO Auto-generated method stub
		
	}

}
