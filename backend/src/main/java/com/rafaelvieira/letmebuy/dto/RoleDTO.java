package com.rafaelvieira.letmebuy.dto;

import com.rafaelvieira.letmebuy.entities.Role;
import lombok.*;

/**
 * @author rafae
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleDTO {
    
    private Long id;
    private String authority;

    public RoleDTO(Role role) {
        super();
        id = role.getId();
        authority = role.getAuthority();
    }
}
