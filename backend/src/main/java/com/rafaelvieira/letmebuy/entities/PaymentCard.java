package com.rafaelvieira.letmebuy.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.rafaelvieira.letmebuy.enums.TypePayment;
import jakarta.persistence.*;
import lombok.*;

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
@EqualsAndHashCode
@JsonTypeName("paymentCard")
@Table(name = "tb_payment_card")
public class PaymentCard extends PaymentMethod {

    @Column(name = "number_of_installments")
    private Integer numberOfInstallments;

//    @ManyToOne
//    @JoinColumn(name = "order_id", insertable = false, updatable = false)
//    private Order order;

    public PaymentCard(Long id, String description, Integer numberOfInstallments) {
        super(id, description);
        this.numberOfInstallments = numberOfInstallments;
    }
}
