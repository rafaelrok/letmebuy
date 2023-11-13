package com.rafaelvieira.letmebuy.dto;

import java.time.Instant;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author rafae
 */

@Getter
@Setter
@NoArgsConstructor
public class OrderByDateDTO {

    private Instant moment;
    private Double sum;

    public OrderByDateDTO(Instant moment, Double sum) {
        this.moment = moment;
        this.sum = sum;
    }
}
