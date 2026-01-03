package com.faq_assistant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.faq_assistant.entity.Tag;
import com.faq_assistant.repository.TagRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagService {
	
	private final TagRepository tagRepository;
	
	public Tag createTag(Tag tag) {
		return tagRepository.save(tag);
	}
	
	public List<Tag> getAllTag() {
		return tagRepository.findAll();
	}
}
