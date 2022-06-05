package com.rafaelvieira.letmebuy.entities;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "tb_feedback")
public class Feedback implements Serializable {
    private static final long serialVersionUID = 1L;
//    @Id
//    @GenericGenerator(name = "UUIDGenerator", strategy = "org.hibernate.id.UUIDGenerator")
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUIDGenerator")
//    @Column(name = "id", updatable = false, nullable = false)
//    @ColumnDefault("random_uuid()")
//    @Type(type = "uuid-char")

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String text;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    public Feedback() {
    }
    public Feedback(Long id, String text, User user, Product product) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.product = product;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Feedback other = (Feedback) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
