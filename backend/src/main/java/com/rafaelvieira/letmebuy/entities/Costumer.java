package com.rafaelvieira.letmebuy.entities;

import com.rafaelvieira.letmebuy.enums.TypeCostumer;
import lombok.*;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author rafae
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "tb_costumer")
public class Costumer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String cpfOuCnpj;

    private Integer type;

    @OneToMany(mappedBy = "costumer", cascade = CascadeType.ALL)
    private List<Address> address = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tb_phone")
    private Set<String> phones = new HashSet<>();

    public Costumer(Long id, String firstName, String lastName, String cpfOuCnpj, TypeCostumer type) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpfOuCnpj = cpfOuCnpj;
        this.type = (type == null) ? null : type.getCode();
    }

    public Costumer(Long id, String firstName, String lastName, String cpfOuCnpj, Integer toEnum) {
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
        Costumer other = (Costumer) obj;
        if (id == null) {
            return other.id == null;
        } else {
            return id.equals(other.id);
        }
    }

    public static Costumer of(Costumer costumer) {
        return Costumer.builder()
                .id(costumer.getId())
                .firstName(costumer.getFirstName())
                .lastName(costumer.getLastName())
                .cpfOuCnpj(costumer.getCpfOuCnpj())
                .type(TypeCostumer.toEnum(costumer.getType()))
                .address(costumer.getAddress())
                .build();
    }
}
