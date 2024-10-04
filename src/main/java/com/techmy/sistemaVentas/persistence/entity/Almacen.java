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
public class Almacen {

	@Id
	private Integer idalmacen;
	@NotBlank
	private String codigoarticulo;
	@NotBlank
	private Integer cantidad;
	private String nombre;
	private Integer trabajadorid;
	private Date fecharegistro;

}
