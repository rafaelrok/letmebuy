package com.rafaelvieira.letmebuy.dto;

import com.rafaelvieira.letmebuy.entities.*;
import com.rafaelvieira.letmebuy.enums.OrderStatus;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
public class OrderDTO {

    private Integer id;
    private Instant moment;
    private Payment payment;
    private OrderStatus status;
    private String costumer;
    private String addressDelivery;

    private List<OrderItemPKDTO> itens = new ArrayList<>();
    private double amount;

    public OrderDTO(Order entity){
        id=entity.getId();
        moment=entity.getMoment();
        payment = entity.getPayment().getOrder().getPayment();
        status = entity.getStatus();
        costumer = entity.getUser().getCostumer().getFirstName();
        addressDelivery = entity.getAddressDelivery().getStreet();
        itens = entity.getItens().stream().map(x -> new OrderItemPKDTO(x.getProduct().getDescription())).collect(Collectors.toList());
        amount = entity.getAmauntValue();
    }
}
