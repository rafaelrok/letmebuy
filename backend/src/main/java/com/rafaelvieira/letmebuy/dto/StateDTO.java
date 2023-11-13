package com.rafaelvieira.letmebuy.dto;

import com.rafaelvieira.letmebuy.entities.State;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author rafae
 */
@Getter
public class StateDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;

    public StateDTO() {
    }

    public StateDTO(State entity) {
        id = entity.getId();
        name = entity.getName();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
