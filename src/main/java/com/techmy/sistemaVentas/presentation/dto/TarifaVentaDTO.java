package com.techmy.sistemaVentas.presentation.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarifaVentaDTO {

	private Integer idtarifaventa;
	private BigDecimal precioventa;
	private String estado;
	private Integer userauthid;
	private String codigoarticulo;
	private Date fecharegistro;
}
