package com.techmy.sistemaVentas.presentation.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {

	private String nombre;
	private String descripcion;
	private Integer estado;
	private Date fecharegistro;
}
