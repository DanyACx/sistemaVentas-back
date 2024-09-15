package com.techmy.sistemaVentas.persistence.entity;
import java.util.Date;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAuth {

	private Integer iduserauth;
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	private Date fecharegistro;
	private Integer bloqueado;
	private Integer baja;
	private Integer personaid;
	private Integer idrole;
	private Set<Role> roles;
	
}
