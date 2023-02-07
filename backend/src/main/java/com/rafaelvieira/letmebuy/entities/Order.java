package com.rafaelvieira.letmebuy.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rafaelvieira.letmebuy.enums.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

/**
 * @author rafae
 */

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_order")
public class Order  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    @OneToOne(cascade=CascadeType.ALL, mappedBy="order")
    private Payment payment;

    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="address_delivery_id")
    private Address addressDelivery;

    @OneToMany(mappedBy="orderItemPK.order")
    private Set<OrderItem> itens = new HashSet<>();

    private Double amount;

    public Order(Integer id, LocalDate date, User user, Address addressDelivery, Payment payment, OrderStatus status, Double amount) {
        super();
        this.id = id;
        this.date = date;
        this.user = user;
        this.addressDelivery = addressDelivery;
        this.payment = payment;
        this.status = status;
        this.amount = getAmauntValue();
    }

    public double getAmauntValue() {
        double sum = 0.0;
        for (OrderItem x : itens) {
            sum = sum + x.getSubTotal();
        }
        return sum;
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder builder = new StringBuilder();
        builder.append("Pedido número: ");
        builder.append(getId());
        builder.append(", Instante: ");
        builder.append(getDate());
        builder.append(", Cliente: ");
        builder.append(getUser().getCostumer().getFirstName());
        builder.append(", Situação do pagamento: ");
        builder.append(getPayment().getTypePayment());
        builder.append(", Situação do pedido: ");
        builder.append(getStatus());
        builder.append("\nDetalhes:\n");
        for (OrderItem ip : getItens()) {
            builder.append(ip.toString());
        }
        builder.append("Valor total: ");
        builder.append(nf.format(getAmauntValue()));
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Order order = (Order) o;
        return id != null && Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
