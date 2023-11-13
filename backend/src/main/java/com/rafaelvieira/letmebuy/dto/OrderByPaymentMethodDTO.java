package com.rafaelvieira.letmebuy.dto;

import com.rafaelvieira.letmebuy.entities.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author rafae
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderByPaymentMethodDTO {

    private String description;
    private Double sum;

    public OrderByPaymentMethodDTO(PaymentMethod paymentMethod, Double sum) {
        this.description = paymentMethod.getDescription();
        this.sum = sum;
    }
}
