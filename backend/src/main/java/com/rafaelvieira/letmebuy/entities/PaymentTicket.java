package com.rafaelvieira.letmebuy.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.rafaelvieira.letmebuy.enums.TypePayment;
import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serial;
import java.time.Instant;
import java.util.Date;

/**
 * @author rafae
 */

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "tb_payment_ticket")
@JsonTypeName("paymentTicket")
public class PaymentTicket extends PaymentMethod {

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dueDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date paymentDate;

//    @ManyToOne
//    @JoinColumn(name = "order_id", insertable = false, updatable = false)
//    private Order order;

    public PaymentTicket(Long id, String description, Date dueDate, Date paymentDate) {
        super(id, description);
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
    }


}
