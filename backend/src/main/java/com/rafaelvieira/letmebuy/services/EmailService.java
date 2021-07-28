package com.rafaelvieira.letmebuy.services;

import com.rafaelvieira.letmebuy.dto.EmailDTO;

public interface EmailService {
    void sendEmail(EmailDTO dto);
}
