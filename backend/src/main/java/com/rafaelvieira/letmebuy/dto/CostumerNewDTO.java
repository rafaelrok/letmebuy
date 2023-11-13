package com.rafaelvieira.letmebuy.dto;


import com.rafaelvieira.letmebuy.services.validation.CostumerInsert;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 * @author rafae
 */

@Getter
@Setter
@RequiredArgsConstructor
@CostumerInsert
public class CostumerNewDTO {

    private Long id;

    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
    private String firstName;

    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
    private String lastName;

    @NotEmpty(message="Preenchimento obrigatório")
    @Email(message="Email inválido")
    private String email;

    @NotEmpty(message="Preenchimento obrigatório")
    private String password;

    @NotEmpty(message="Preenchimento obrigatório")
    private String cpfOuCnpj;

    private Integer type;

    @NotEmpty(message="Preenchimento obrigatório")
    private String street;

    @NotEmpty(message="Preenchimento obrigatório")
    private String number;

    private String complement;

    @NotEmpty(message="Preenchimento obrigatório")
    private String neighborhood;

    @NotEmpty(message="Preenchimento obrigatório")
    private String zipcode;

    @NotEmpty(message="Preenchimento obrigatório")
    private String phone1;

    private String phone2;

    private String phone3;

    private Integer cityId;

}
