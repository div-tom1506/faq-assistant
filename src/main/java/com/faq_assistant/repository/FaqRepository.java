package com.faq_assistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.faq_assistant.entity.Faq;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long>{

}
