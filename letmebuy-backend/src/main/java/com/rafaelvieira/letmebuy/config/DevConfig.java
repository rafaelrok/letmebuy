package com.rafaelvieira.letmebuy.config;

import com.rafaelvieira.letmebuy.services.EmailService;
import com.rafaelvieira.letmebuy.services.SendGridEmailService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {
    //Bean responsavel por enviar email no profile dev
    @Bean
    public EmailService emailService() {
        return new SendGridEmailService();
    }
}
