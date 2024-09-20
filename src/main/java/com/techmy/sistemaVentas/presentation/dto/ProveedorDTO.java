package com.techmy.sistemaVentas.presentation.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorDTO {

	private String ruc;
	private String razonsocial;
	private String banco;
	private String telefono;
	private Date fecharegistro;
}
