package com.rafaelvieira.letmebuy.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.rafaelvieira.letmebuy.enums.TypePayment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author rafae
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_payment_ticket")
@JsonTypeName("paymentTicket")
public class PaymentTicket extends Payment {
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dueDate;
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date paymentDate;

    public PaymentTicket(Integer id, TypePayment status, Order order, Date dueDate, Date paymentDate) {
        super(id, status, order);
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
    }
}
