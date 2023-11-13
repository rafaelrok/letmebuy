package com.rafaelvieira.letmebuy.dto;

import com.rafaelvieira.letmebuy.services.validation.UserInsertValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.io.Serial;

/**
 * @author rafae
 */
@Getter
@UserInsertValid
public class UserInsertDTO extends UserDTO {

    @NotBlank(message = "Campo requerido")
    @Size(min = 8, message = "Deve ter no mínimo 8 caracteres")
    private String password;

    UserInsertDTO() {
        super();
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
