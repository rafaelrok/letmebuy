package com.rafaelvieira.letmebuy.dto;

import com.rafaelvieira.letmebuy.entities.Role;
import lombok.*;

import java.io.Serializable;

/**
 * @author rafae
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String authority;

    public RoleDTO(Role role) {
        super();
        id = role.getId();
        authority = role.getAuthority();
    }
}
