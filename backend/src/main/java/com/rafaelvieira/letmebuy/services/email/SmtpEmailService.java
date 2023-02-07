package com.rafaelvieira.letmebuy.services.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @author rafae
 */

public class SmtpEmailService extends AbstractEmailService {

    @Autowired(required = false)
    private MailSender mailSender;

    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("Enviando email...");
        mailSender.send(msg);
        LOG.info("Email enviado");
    }

}
