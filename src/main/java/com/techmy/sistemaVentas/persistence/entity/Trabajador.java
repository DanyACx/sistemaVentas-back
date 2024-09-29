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
public class Trabajador {

	@Id
	private Integer idtrabajador;
	@NotBlank
	private String codigo;
	private Integer activotrabajador;
	private Date fechacese;
	private Integer personaid;
	private Date fecharegistro; // import java.util.Date; para fecha normal 
}
