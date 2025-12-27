package com.faq_assistant.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.faq_assistant.entity.Faq;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long>{

	@Query("""
			SELECT DISTINCT f FROM Faq f
			LEFT JOIN f.tags t 
			WHERE (:search IS NULL OR LOWER(f.question) LIKE LOWER(CONCAT('%', :search, '%')))
			AND (:categoryId IS NULL OR f.category.id = :categoryId)
			AND (:tagIds IS NULL OR t.id IN :tagIds)
	""")
	Page<Faq> searchFaqs (
			@Param("search") String search,
	        @Param("categoryId") Long categoryId,
	        @Param("tagIds") List<Long> tagIds,
	        Pageable pageable
	    );
}
