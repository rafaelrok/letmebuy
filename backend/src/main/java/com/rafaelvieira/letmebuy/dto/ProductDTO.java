package com.rafaelvieira.letmebuy.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.rafaelvieira.letmebuy.entities.Category;
import com.rafaelvieira.letmebuy.entities.Feedback;
import com.rafaelvieira.letmebuy.entities.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class ProductDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @Size(min = 2, max = 60, message = "Deve ter entre 5 e 60 caracteres")
    @NotBlank(message = "Campo requirido")
    private String name;
    @NotBlank(message = "Campo requirido")
    private String description;
    @Positive(message = "Preço deve ser positivo")
    private Double price;
    private String imgUrl;
    @PastOrPresent(message = "Data do produto futura")
    private Instant date;
    private List<CategoryDTO> categories = new ArrayList<>();

    private List<FeedbackDTO> feedbacks = new ArrayList<>();

    public ProductDTO() { }

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl, Instant date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.date = date;
    }

    public ProductDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        price = entity.getPrice();
        imgUrl = entity.getImgUrl();
        date = entity.getDate();
        entity.getFeedbacks().forEach(FeedbackDTO::new);
    }

    //Sobrecarga de dados, percorre e adiciona uma nova categoria a lista de List<categoryDTO>
    public ProductDTO(Product entity, Set<Category> categories, List<Feedback> feedbacks) {
        this(entity);
        categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));
        feedbacks.forEach(x -> this.feedbacks.add(new FeedbackDTO(x)));
    }

//    public ProductDTO(Product entity, List<Feedback> feedbacks) {
//        this(entity);
//        feedbacks.forEach(x -> this.feedbacks.add(new FeedbackDTO(x)));
//    }

    //#region
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public List<FeedbackDTO> getFeedbacks() {
        return feedbacks;
    }
    //#endregion


}
