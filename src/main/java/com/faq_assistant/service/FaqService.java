package com.faq_assistant.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.faq_assistant.dto.FaqRequestDto;
import com.faq_assistant.dto.FaqResponseDto;

public interface FaqService {

	FaqResponseDto createFaq(FaqRequestDto request);
	FaqResponseDto getFaqById(Long id);
	Page<FaqResponseDto> searchFaqs(String search, Long categoryId, List<Long> tagids, Pageable pageable);
	FaqResponseDto updateFaq(Long id, FaqRequestDto request);
	void deleteFaq(Long id);
}
