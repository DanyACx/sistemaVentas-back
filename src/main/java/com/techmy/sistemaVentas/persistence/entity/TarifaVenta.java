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
public class TarifaVenta {

	@Id
	private Integer idtarifaventa;
	@NotBlank
	private BigDecimal precioventa;
	@NotBlank
	private String estado;
	private Integer userauthid;
	private String codigoarticulo;
	private Date fecharegistro;
}
