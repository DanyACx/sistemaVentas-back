package com.techmy.sistemaVentas.persistence.entity;

import java.util.Date;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Categoria {

	@Id
	private Integer idcategoria;
	@NotBlank
	private String nombre;
	@NotBlank
	private String descripcion;
	private Integer estado;
	private Date fecharegistro; // import java.util.Date; para fecha normal 
}
