package com.rafaelvieira.letmebuy.entities;

import com.rafaelvieira.letmebuy.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

/**
 * @author rafae
 */

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "tb_order")
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Payment payment;

    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "address_delivery_id")
    private Address addressDelivery;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> itens = new HashSet<>();

    private Double amount;

    public Order(Integer id, Instant moment, User user, Address addressDelivery, Payment payment, OrderStatus status) {
        super();
        this.id = id;
        this.moment = moment;
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

    public List<Product> getProducts() {
        return itens.stream().map(OrderItem::getProduct).toList();
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder builder = new StringBuilder();
        builder.append("Pedido número: ");
        builder.append(getId());
        builder.append(", Instante: ");
        builder.append(sdf.format(getMoment()));
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
