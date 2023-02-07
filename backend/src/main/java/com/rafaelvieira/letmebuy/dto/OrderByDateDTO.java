package com.rafaelvieira.letmebuy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * @author rafae
 */

@Getter
@Setter
@NoArgsConstructor
public class OrderByDateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDate date;
    private Double sum;

    public OrderByDateDTO(LocalDate date, Double sum) {
        this.date = date;
        this.sum = sum;
    }
}
