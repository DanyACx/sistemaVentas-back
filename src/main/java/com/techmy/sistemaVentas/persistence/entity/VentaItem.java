package com.techmy.sistemaVentas.persistence.entity;


import java.math.BigDecimal;

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
public class VentaItem {

	@Id
	private Integer idventaitem;
	@NotBlank
	private String codigoarticulo;
	@NotBlank
	private Integer cantidad;
	private BigDecimal preciounitario;
	private Integer ventaid;
}
