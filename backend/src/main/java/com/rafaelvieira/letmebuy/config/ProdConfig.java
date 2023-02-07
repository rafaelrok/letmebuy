package com.rafaelvieira.letmebuy.config;

import com.rafaelvieira.letmebuy.services.email.EmailService;
import com.rafaelvieira.letmebuy.services.email.SmtpEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author rafae
 */
@Configuration
@Profile("prod")
public class ProdConfig {

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }
}
