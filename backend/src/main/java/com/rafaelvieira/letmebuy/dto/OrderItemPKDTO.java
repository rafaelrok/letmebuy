package com.rafaelvieira.letmebuy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemPKDTO {
    private String product;

    public OrderItemPKDTO(String product) {
        this.product = product;
    }


}

