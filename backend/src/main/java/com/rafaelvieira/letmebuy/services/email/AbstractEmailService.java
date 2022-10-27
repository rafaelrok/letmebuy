package com.rafaelvieira.letmebuy.services.email;

import java.util.Date;

import com.rafaelvieira.letmebuy.entities.Costumer;
import com.rafaelvieira.letmebuy.entities.Order;
import com.rafaelvieira.letmebuy.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;



/**
 * @author rafae
 */

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;


    public void sendOrderConfirmationEmail(Order obj) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Order obj) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getCostumer().getUser().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Pedido confirmado! Código: " + obj.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(obj.toString());
        return sm;
    }

    @Override
    public void sendNewPasswordEmail(User user, String newPass) {
        SimpleMailMessage sm = prepareNewPasswordEmail(user, newPass);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareNewPasswordEmail(User user, String newPass) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(user.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Solicitação de nova senha");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Nova senha: " + newPass);
        return sm;
    }
}
