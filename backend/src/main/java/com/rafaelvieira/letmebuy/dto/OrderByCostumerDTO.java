package com.rafaelvieira.letmebuy.dto;

import com.rafaelvieira.letmebuy.entities.Costumer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author rafae
 */

@Getter
@Setter
@NoArgsConstructor
public class OrderByCostumerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String costumerName;
    private Double sum;

    public OrderByCostumerDTO(Costumer costumer, Double sum) {
        this.costumerName =costumer.getFirstName();
        this.sum = sum;
    }
}
