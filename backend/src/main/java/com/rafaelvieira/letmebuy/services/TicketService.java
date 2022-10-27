package com.rafaelvieira.letmebuy.services;

import com.rafaelvieira.letmebuy.entities.PaymentTicket;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

/**
 * @author rafae
 */

@Service
public class TicketService {
    public static void fillPaymentWithTicket(PaymentTicket ticket, Instant instantOrder) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.from(instantOrder));
        cal.add(Calendar.DAY_OF_MONTH, 7);
        ticket.setDueDate(cal.getTime());
    }
}
