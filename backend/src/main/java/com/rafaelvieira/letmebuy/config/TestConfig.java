package com.rafaelvieira.letmebuy.config;

import com.rafaelvieira.letmebuy.services.email.EmailService;
import com.rafaelvieira.letmebuy.services.email.MockEmailService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author rafae
 */
@Configuration
@Profile("test")
public class TestConfig {

    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }
}
