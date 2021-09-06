package com.rafaelvieira.letmebuy.services;

import com.rafaelvieira.letmebuy.dto.EmailDTO;
import org.springframework.context.annotation.Bean;

public interface EmailService {
    @Bean
    void sendEmail(EmailDTO dto);
}
