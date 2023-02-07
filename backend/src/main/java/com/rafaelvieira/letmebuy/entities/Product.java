package com.rafaelvieira.letmebuy.entities;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

/**
 * @author rafae
 */

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private Double price;
    private String imgUrl;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant date;
    @ManyToMany
    @JoinTable(name = "tb_product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    Set<Category> categories = new HashSet<>();
    @OneToMany(mappedBy = "product")
    private List<Feedback> feedbacks = new ArrayList<>();

    public Product(long l, String string, String string2, double d, String string3, Instant instant, boolean b) {
    }

    public void prePersist() {
        date = Instant.now();
    }

    public Product(Long id, String name, String description, Double price, String imgUrl, Instant date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.date = date;
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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Product other = (Product) obj;
        if (id == null) {
            return other.id == null;
        } else {
            return id.equals(other.id);
        }
    }

    public static Product of(Product product) {
        return Product.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .imgUrl(product.getImgUrl())
                .date(product.getDate())
                .categories(product.getCategories())
                .build();
    }

}
