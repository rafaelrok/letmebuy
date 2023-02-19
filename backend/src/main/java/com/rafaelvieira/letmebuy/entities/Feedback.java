package com.rafaelvieira.letmebuy.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
/**
 * @author rafae
 */
@Getter
@Setter
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
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
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


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
        Feedback other = (Feedback) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }
}
