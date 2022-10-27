package com.rafaelvieira.letmebuy.services.email;

import com.rafaelvieira.letmebuy.entities.Order;
import com.rafaelvieira.letmebuy.entities.User;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author rafae
 */
public interface EmailService {

    void sendOrderConfirmationEmail(Order obj);
    void sendEmail(SimpleMailMessage msg);
    void sendNewPasswordEmail(User user, String newPass);
}
