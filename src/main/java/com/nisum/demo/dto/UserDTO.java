package com.nisum.demo.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.nisum.demo.validator.StrongPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private UUID id;
	@NotBlank(message = "Name cannot be blank")
	private String name;
	@StrongPassword
	private String password;
	//@Email(regexp = "^[A-Za-z0-9+_.-]+@dominio.cl", message = "INVALID_EMAIL")
	@Email(message = "INVALID_EMAIL", flags = { Pattern.Flag.CASE_INSENSITIVE }, regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
	private String email;
	private LocalDateTime created;
	private String token;
	private Boolean isActive;
	private LocalDateTime lastLogin;
	private List<PhoneDTO> phones;
	

}
