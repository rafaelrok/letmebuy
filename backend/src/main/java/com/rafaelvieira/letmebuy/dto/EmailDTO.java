package com.rafaelvieira.letmebuy.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author rafae
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {
    

    @NotEmpty(message="Preenchimento obrigatório")
    @Email(message="Email inválido")
    private String email;
}
