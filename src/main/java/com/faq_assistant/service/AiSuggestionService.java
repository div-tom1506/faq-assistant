package com.faq_assistant.service;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.faq_assistant.config.AiConfig;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AiSuggestionService {

    private final AiConfig aiConfig;
    private final RestTemplate restTemplate = new RestTemplate();

    @Cacheable(
            value = "ai_faq_suggestions",
            key = "#question"
    )
    public String suggestAnswer(String question) {
        String prompt = """
	            You are an internal FAQ assistant.
	            Generate a clear and concise answer for the following question:

	            Question: %s
	            """.formatted(question);

        return callAi(prompt);
    }

    private String callAi(String prompt) {

        String url = "https://generativelanguage.googleapis.com/v1beta/models/"
                + aiConfig.getModel()
                + ":generateContent?key="
                + aiConfig.getApiKey();

        Map<String, Object> body = Map.of(
                "contents", List.of(
                        Map.of(
                                "parts", List.of(
                                        Map.of("text", prompt)
                                )
                        )
                )
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        Map<?, ?> response
                = restTemplate.postForObject(url, request, Map.class);

        return extractText(response);
    }

    private String extractText(Map<?, ?> response) {
        try {
            var candidates = (List<?>) response.get("candidates");
            var content = (Map<?, ?>) candidates.get(0);
            var parts = (List<?>) ((Map<?, ?>) content.get("content")).get("parts");
            return parts.get(0).get("text").toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Gemini response");
        }
    }
}
