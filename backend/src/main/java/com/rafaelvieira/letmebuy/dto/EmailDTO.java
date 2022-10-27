package com.rafaelvieira.letmebuy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author rafae
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotEmpty(message="Preenchimento obrigatório")
    @Email(message="Email inválido")
    private String email;
}
