package com.techmy.sistemaVentas.presentation.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDTO {


	private String nombres;
	private String appaterno;
	private String apmaterno;
	private String tipopersona;
	private String tipodocumento;
	private String numdocumento;
	private String direccion;
	private String telefono;
	private String email;
	private Date fechacreacion;
}
