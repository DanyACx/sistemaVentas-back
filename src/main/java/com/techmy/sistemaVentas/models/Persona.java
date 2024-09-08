package com.techmy.sistemaVentas.models;
import java.util.Date;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Persona {

	@Id
	private Integer idpersona;
	@NotBlank
	private String nombres;
	@NotBlank
	private String appaterno;
	private String apmaterno;
	private String tipopersona;
	private String tipodocumento;
	@NotBlank
	private String numdocumento;
	private String direccion;
	private String telefono;
	@Email
	private String email;
	private Date fechacreacion; // import java.util.Date; para fecha normal 
}
