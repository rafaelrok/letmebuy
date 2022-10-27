package com.rafaelvieira.letmebuy.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author rafae
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_order_item")
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private OrderItemPK orderItemPK = new OrderItemPK();
    private Double discount;
    private Integer quantity;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem(Order order, Product product, Double discount, Integer quantity, Double price) {
        super();
        orderItemPK.setOrder(order);
        orderItemPK.setProduct(product);
        this.discount = discount;
        this.quantity = quantity;
        this.price = price;
    }

    public double getSubTotal() {
        return (price - discount) * quantity;
    }

    @JsonIgnore
    public Order getOrder() {
        return orderItemPK.getOrder();
    }

    public void setOrder(Order order) {
        orderItemPK.setOrder(order);
    }

    public Product getProduct() {
        return orderItemPK.getProduct();
    }

    public void setProduct(Product product) {
        orderItemPK.setProduct(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        OrderItem orderItem = (OrderItem) o;
        return orderItemPK != null && Objects.equals(orderItemPK, orderItem.orderItemPK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderItemPK);
    }
}
