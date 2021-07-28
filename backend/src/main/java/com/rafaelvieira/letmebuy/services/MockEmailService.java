package com.rafaelvieira.letmebuy.services;

import com.rafaelvieira.letmebuy.dto.EmailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MockEmailService implements EmailService {

    private static Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    public void sendEmail(EmailDTO dto){
            LOG.info("Sending email to: " + dto.getTo());
            LOG.info("Email sent!");
    }
}
