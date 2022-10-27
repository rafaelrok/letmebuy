package com.rafaelvieira.letmebuy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author rafae
 */

@Getter
@Setter
@NoArgsConstructor
public class OrderByDateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Instant instant;
    private Double sum;

    public OrderByDateDTO(Instant instant, Double sum) {
        this.instant = instant;
        this.sum = sum;
    }
}
