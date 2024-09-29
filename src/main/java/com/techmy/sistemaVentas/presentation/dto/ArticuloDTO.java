package com.techmy.sistemaVentas.presentation.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticuloDTO {

	private Integer idarticulo;
	private String nombre;
	private String descripcion;
	private BigDecimal preciounidad;
	private Integer cantidad;
	private Integer estadoarticulo;
	private String codigoarticulo;
	private Integer categoriaid;
	private Integer ingresoid;
}
