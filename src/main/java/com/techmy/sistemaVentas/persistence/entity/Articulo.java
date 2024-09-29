package com.techmy.sistemaVentas.persistence.entity;

import java.math.BigDecimal;
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
public class Articulo {

	@Id
	private Integer idarticulo;
	@NotBlank
	private String codigoserial;
	@NotBlank
	private String nombre;
	private String descripcion;
	private BigDecimal preciounidad;
	private Integer cantidad;
	private Integer estadoarticulo;
	private String codigoarticulo;
	private Integer categoriaid;
	private Integer ingresoid;
}
