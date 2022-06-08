package com.rafaelvieira.letmebuy.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.rafaelvieira.letmebuy.entities.Costumer;
import com.rafaelvieira.letmebuy.entities.Feedback;
import com.rafaelvieira.letmebuy.entities.User;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@Email(message = "Favor entrar um email válido")
	private String email;
	Set<RoleDTO> roles = new HashSet<>();

//	private List<FeedbackDTO> feedbacks = new ArrayList<>();
//
//	private List<CostumerDTO> costumers = new ArrayList<>();

	public UserDTO() {
	}

	public UserDTO(Long id, String email) {
		this.id = id;
		this.email = email;
	}

	public UserDTO(User entity) {
		id = entity.getId();
		email = entity.getEmail();
		entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
	}

//	public UserDTO(User entity, List<Feedback> feedbacks, List<Costumer> costumers) {
//		this(entity);
//		feedbacks.forEach(x -> this.feedbacks.add(new FeedbackDTO(x)));
//		costumers.forEach(x -> this.costumers.add(new CostumerDTO(x)));
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<RoleDTO> getRoles() {
		return roles;
	}

//	public List<FeedbackDTO> getFeedbacks() {
//		return feedbacks;
//
//	}
//	public List<CostumerDTO> getCostumers() {
//		return costumers;
//	}
}