package com.rafaelvieira.letmebuy.dto;


import com.rafaelvieira.letmebuy.entities.Category;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * @author rafae
 */
@Getter
public class CategoryDTO {

    private Long id;

    @NotBlank(message = "Campo requirido")
    private String name;

    public CategoryDTO() {}

    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDTO(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
