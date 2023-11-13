package com.rafaelvieira.letmebuy.dto;

import com.rafaelvieira.letmebuy.entities.User;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author rafae
 */

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {

	private Long id;

	@Email(message = "Favor entrar com email válido")
	private String email;

	Set<RoleDTO> roles = new HashSet<>();

//	private List<FeedbackDTO> feedbacks = new ArrayList<>();
//
//	private List<CostumerDTO> costumers = new ArrayList<>();

	public UserDTO() {
	}

	public UserDTO(Long id,  String email) {
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

//	public List<FeedbackDTO> getFeedbacks() {
//		return feedbacks;
//
//	}
//	public List<CostumerDTO> getCostumers() {
//		return costumers;
//	}
}