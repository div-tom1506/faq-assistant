package com.faq_assistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.faq_assistant.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
