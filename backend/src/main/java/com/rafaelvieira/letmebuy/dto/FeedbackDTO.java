package com.rafaelvieira.letmebuy.dto;

import com.rafaelvieira.letmebuy.entities.Feedback;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class FeedbackDTO {

    private Long id;

    @NotBlank(message ="Campo requerido")
    private String text;

    private Long productId;

    private UserDTO user;

    public FeedbackDTO() {
    }
    public FeedbackDTO(Feedback entity) {
        id = entity.getId();
        text = entity.getText();
        user = new UserDTO(entity.getUser());
        productId = entity.getProduct().getId();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
