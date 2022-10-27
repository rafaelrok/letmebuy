package com.rafaelvieira.letmebuy.dto;

import com.rafaelvieira.letmebuy.entities.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author rafae
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderByPaymentMethodDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String description;
    private Double sum;

    public OrderByPaymentMethodDTO(PaymentMethod paymentMethod, Double sum) {
        this.description = paymentMethod.getDescription();
        this.sum = sum;
    }
}
