package com.rafaelvieira.letmebuy.dto;

import com.rafaelvieira.letmebuy.entities.City;
import lombok.Getter;

@Getter
public class CityDTO {

    private Integer id;
    private String name;

    public CityDTO() {
    }

    public CityDTO(City entity) {
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
