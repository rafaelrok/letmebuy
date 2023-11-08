package com.rafaelvieira.letmebuy.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.rafaelvieira.letmebuy.enums.TypePayment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serial;
import java.time.Instant;

/**
 * @author rafae
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonTypeName("paymentCard")
@Table(name = "tb_payment_card")
public class PaymentCard extends Payment {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer numberOfInstallments;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;

    public PaymentCard(TypePayment status, Order order, Integer numberOfInstallments, Instant moment) {
        super(numberOfInstallments, status, moment, order);
        this.numberOfInstallments = numberOfInstallments;
    }
}
