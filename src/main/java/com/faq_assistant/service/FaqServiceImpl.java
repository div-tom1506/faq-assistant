package com.faq_assistant.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faq_assistant.dto.FaqRequestDto;
import com.faq_assistant.dto.FaqResponseDto;
import com.faq_assistant.entity.Category;
import com.faq_assistant.entity.Faq;
import com.faq_assistant.entity.Tag;
import com.faq_assistant.entity.Users;
import com.faq_assistant.repository.CategoryRepository;
import com.faq_assistant.repository.FaqRepository;
import com.faq_assistant.repository.TagRepository;
import com.faq_assistant.repository.UserRepository;

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
		
		Set<Tag> tags = new HashSet<>();
		if(request.getTagIds() != null) {
			tags.addAll(tagRepository.findAllById(request.getTagIds()));
		}
		
		Faq faq = new Faq();
		faq.setQuestion(request.getQuestion());
		faq.setAnswer(request.getAnswer());
		faq.setCategory(category);
		faq.setCreatedBy(users);
		faq.setTags(tags);
		
		return mapToResponse(faqRepository.save(faq));
	}

	@Override
	@Transactional(readOnly = true)
	public FaqResponseDto getFaqById(Long id) {
		return faqRepository.findById(id)
				.map(this::mapToResponse)
				.orElseThrow(() -> new ResourceNotFoundException("FAQ not found"));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<FaqResponseDto> searchFaqs(String search, Long categoryId, List<Long> tagids, Pageable pageable) {
		return faqRepository.searchFaqs(search, categoryId, tagids, pageable)
				.map(this::mapToResponse);
	}

	@Override
	public FaqResponseDto updateFaq(Long id, FaqRequestDto request) {
		Faq faq = faqRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FAQ not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Users user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        faq.setQuestion(request.getQuestion());
        faq.setAnswer(request.getAnswer());
        faq.setCategory(category);
        faq.setUpdatedBy(user);

        if (request.getTagIds() != null) {
            faq.setTags(new HashSet<>(tagRepository.findAllById(request.getTagIds())));
        }

        return mapToResponse(faqRepository.save(faq));
	}

	@Override
	public void deleteFaq(Long id) {
		if((!faqRepository.existsById(id))) {
			throw new ResourceNotFoundException("FAQ not found")
		}
		faqRepository.deleteById(id);
	}
	
	private FaqResponseDto mapToResponse(Faq faq) {
		
		FaqResponseDto dto = new FaqResponseDto();
		dto.setId(faq.getId());
		dto.setQuestion(faq.getQuestion());
		dto.setAnswer(faq.getAnswer());
		dto.setCategory(faq.getCategory().getName());
		dto.setTag(
				faq.getTags().stream()
					.map(Tag::getName)
					.toList()
		);
		dto.setCreatedBy(faq.getCreatedBy().getName());
//		dto.setCreatedAt(faq.getCreatedAt());
		
		return dto;
		
	}

}
