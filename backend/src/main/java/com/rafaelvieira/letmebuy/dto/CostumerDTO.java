package com.rafaelvieira.letmebuy.dto;

import com.rafaelvieira.letmebuy.entities.*;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

/**
 * @author rafae
 */

@Getter
public class CostumerDTO {

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
