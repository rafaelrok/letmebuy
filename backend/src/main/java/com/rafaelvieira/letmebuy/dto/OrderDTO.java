package com.rafaelvieira.letmebuy.dto;

import com.rafaelvieira.letmebuy.entities.*;
import com.rafaelvieira.letmebuy.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rafae
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private LocalDate date;
    private Payment payment;
    private OrderStatus status;
    private String costumer;
    private String addressDelivery;

    private List<OrderItemPKDTO> itens = new ArrayList<>();
    private double amount;

    public OrderDTO(Order entity){
        id=entity.getId();
        date=entity.getDate();
        payment = entity.getPayment().getOrder().getPayment();
        status = entity.getStatus();
        costumer = entity.getUser().getCostumer().getFirstName();
        addressDelivery = entity.getAddressDelivery().getStreet();
        itens = entity.getItens().stream().map(x -> new OrderItemPKDTO(x.getProduct().getDescription())).collect(Collectors.toList());
        amount = entity.getAmauntValue();
    }
}
