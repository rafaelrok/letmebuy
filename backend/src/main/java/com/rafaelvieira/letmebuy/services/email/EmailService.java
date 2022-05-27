package com.rafaelvieira.letmebuy.services.email;

import com.rafaelvieira.letmebuy.dto.EmailDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

@EnableJpaRepositories
@Service
public interface EmailService {

    @Bean
    void sendEmail(EmailDTO dto);
}
