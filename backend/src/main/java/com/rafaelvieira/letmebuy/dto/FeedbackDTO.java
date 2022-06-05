package com.rafaelvieira.letmebuy.dto;

import com.rafaelvieira.letmebuy.entities.Feedback;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
public class FeedbackDTO implements Serializable {
    private static final long serialVersionUID = 1L;
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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
