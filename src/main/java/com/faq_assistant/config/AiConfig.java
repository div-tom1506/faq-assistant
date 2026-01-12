package com.faq_assistant.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Value()
    private String apiKey;

    @Value
    private String model;

}
