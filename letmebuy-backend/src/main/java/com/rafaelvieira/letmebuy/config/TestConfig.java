package com.rafaelvieira.letmebuy.config;

import com.rafaelvieira.letmebuy.services.EmailService;
import com.rafaelvieira.letmebuy.services.MockEmailService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {
    //Bean responsavel por enviar email no profile test.
    @Bean
    public EmailService emailService() {

        return new MockEmailService();
    }
}
