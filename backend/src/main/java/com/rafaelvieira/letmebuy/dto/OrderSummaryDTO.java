package com.rafaelvieira.letmebuy.dto;

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
@NoArgsConstructor
public class OrderSummaryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Double sum;
    private Double min;
    private Double max;
    private Double avg;
    private Long count;

    public OrderSummaryDTO(Double sum, Double min, Double max, Double avg, Long count) {
        super();
        this.sum = sum;
        this.min = min;
        this.max = max;
        this.avg = avg;
        this.count = count;
    }
}
