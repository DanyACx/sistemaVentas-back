package com.techmy.sistemaVentas.dto;

import java.util.Date;
import java.util.Set;

import com.techmy.sistemaVentas.models.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthDTO {

	private String username;
	private String password;
	private Date fechacreacion;
	private Integer bloqueado;
	private Integer baja;
	private Integer personaid;
	private Set<Role> roles;
}
