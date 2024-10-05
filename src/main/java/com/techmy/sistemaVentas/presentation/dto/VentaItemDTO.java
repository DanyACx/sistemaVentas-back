package com.techmy.sistemaVentas.presentation.dto;


import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaItemDTO {

	private Integer idventaitem;
	private String codigoarticulo;
	private Integer cantidad;
	private BigDecimal preciounitario;
	private Integer ventaid;
}
