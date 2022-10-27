package com.rafaelvieira.letmebuy.dto;

import com.rafaelvieira.letmebuy.services.validation.UserInsertValid;

/**
 * @author rafae
 */
@UserInsertValid
public class UserInsertDTO extends UserDTO {
    private static final long serialVersionUID = 1L;

    private String password;

    UserInsertDTO() {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
