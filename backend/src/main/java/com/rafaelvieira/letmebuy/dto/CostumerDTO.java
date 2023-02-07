package com.rafaelvieira.letmebuy.dto;

import com.rafaelvieira.letmebuy.entities.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author rafae
 */

public class CostumerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
    private String firstName;

    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
    private String lastName;

    public CostumerDTO() {
    }

    public CostumerDTO(Costumer entity) {
        id = entity.getId();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
