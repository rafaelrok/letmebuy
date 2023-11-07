package com.rafaelvieira.letmebuy.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;

import com.rafaelvieira.letmebuy.entities.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author rafae
 */

@Getter
@Setter
@AllArgsConstructor
public class UserDTO implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "Campo obrigatório")
	private String firstName;
	private String lastName;

	@Email(message = "Favor entrar com email válido")
	private String email;

	Set<RoleDTO> roles = new HashSet<>();

//	private List<FeedbackDTO> feedbacks = new ArrayList<>();
//
//	private List<CostumerDTO> costumers = new ArrayList<>();

	public UserDTO() {
	}

	public UserDTO(Long id, String firstName, String lastName, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public UserDTO(User entity) {
		id = entity.getId();
		firstName = entity.getFirstName();
		lastName = entity.getLastName();
		email = entity.getEmail();
		entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
	}

//	public UserDTO(User entity, List<Feedback> feedbacks, List<Costumer> costumers) {
//		this(entity);
//		feedbacks.forEach(x -> this.feedbacks.add(new FeedbackDTO(x)));
//		costumers.forEach(x -> this.costumers.add(new CostumerDTO(x)));
//	}

//	public List<FeedbackDTO> getFeedbacks() {
//		return feedbacks;
//
//	}
//	public List<CostumerDTO> getCostumers() {
//		return costumers;
//	}
}