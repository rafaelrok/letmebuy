package com.rafaelvieira.letmebuy.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author rafae
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_order")
public class Order  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date instant;

    @OneToOne(cascade=CascadeType.ALL, mappedBy="order")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name="costumer_id")
    private Costumer costumer;

    @ManyToOne
    @JoinColumn(name="address_delivery_id")
    private Address addressDelivery;

    @OneToMany(mappedBy="orderItemPK.order")
    private Set<OrderItem> itens = new HashSet<>();

    public Order(Integer id, Date instant, Costumer costumer, Address addressDelivery) {
        super();
        this.id = id;
        this.instant = instant;
        this.costumer = costumer;
        this.addressDelivery = addressDelivery;
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder builder = new StringBuilder();
        builder.append("Pedido número: ");
        builder.append(getId());
        builder.append(", Instante: ");
        builder.append(sdf.format(getInstant()));
        builder.append(", Cliente: ");
        builder.append(getCostumer().getFirstName());
        builder.append(", Situação do pagamento: ");
        builder.append(getPayment().getStatus().getDescription());
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
