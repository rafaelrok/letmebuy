package com.rafaelvieira.letmebuy.dto;

import com.rafaelvieira.letmebuy.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author rafae
 */

@Getter
@Setter
@NoArgsConstructor
public class OrderByCostumerDTO {

    private String costumerName;
    private Double sum;

    public OrderByCostumerDTO(User user, Double sum) {
        this.costumerName = user.getCostumer().getFirstName();
        this.sum = sum;
    }
}
