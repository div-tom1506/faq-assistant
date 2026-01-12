package com.faq_assistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FaqAssistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(FaqAssistantApplication.class, args);
    }

}
