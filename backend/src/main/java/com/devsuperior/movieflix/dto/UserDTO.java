package com.devsuperior.movieflix.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.devsuperior.movieflix.entities.User;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank(message = "Campo Obrigatório")
	private String name;
	@Email(message = "Entre com email válido")
	private String email;

	//Set<RoleDTO> roles = new HashSet<>();
	//private Set<ReviewDTO> reviews = new HashSet<>();

	public UserDTO() {

	}

	public UserDTO(Long id, @NotBlank(message = "Campo Obrigatório") String name,
			@Email(message = "Entre com email válido") String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public UserDTO(User entity) {
		super();
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
		//entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
		//entity.getReviews().forEach(review -> this.reviews.add(new ReviewDTO(review)));
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}