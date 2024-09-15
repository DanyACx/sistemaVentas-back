package com.techmy.sistemaVentas.presentation.dto;

import java.util.Date;
import java.util.Set;

import com.techmy.sistemaVentas.persistence.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthDTO {

	private Integer iduserauth;
	private String username;
	private String password;
	private Date fecharegistro;
	private Integer bloqueado;
	private Integer baja;
	private Integer personaid;
	private Integer idrole;
	private Set<Role> roles;
}
