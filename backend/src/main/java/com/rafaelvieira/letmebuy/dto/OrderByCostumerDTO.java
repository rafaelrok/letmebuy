package com.rafaelvieira.letmebuy.dto;

import com.rafaelvieira.letmebuy.entities.Costumer;
import com.rafaelvieira.letmebuy.entities.User;
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

    public OrderByCostumerDTO(User user, Double sum) {
        this.costumerName = user.getCostumer().getFirstName();
        this.sum = sum;
    }
}
