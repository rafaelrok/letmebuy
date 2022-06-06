package com.rafaelvieira.letmebuy.dto;

import com.rafaelvieira.letmebuy.entities.State;

import java.io.Serializable;

public class StateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;

    public StateDTO() {
    }

    public StateDTO(State entity) {
        id = entity.getId();
        name = entity.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
