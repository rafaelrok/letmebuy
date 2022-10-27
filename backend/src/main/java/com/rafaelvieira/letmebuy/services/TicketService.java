package com.rafaelvieira.letmebuy.services;

import com.rafaelvieira.letmebuy.entities.PaymentTicket;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * @author rafae
 */

@Service
public class TicketService {
    public void fillPaymentWithTicket(PaymentTicket pagto, Date instantOrder) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(instantOrder);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        pagto.setDueDate(cal.getTime());
    }
}
