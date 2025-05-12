package com.example.user_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "app_user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Username cannot be null")
	@Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
	private String username;

	@NotNull(message = "Email cannot be null")
	@Email(message = "Please provide a valid email address")
	private String email;

	@Enumerated(EnumType.STRING)
	private Role role;

}
