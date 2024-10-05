package com.techmy.sistemaVentas.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Venta {

	private Integer idventa;
	private String codigoventa;
	private Integer estadoventa;
	private Integer trabajadorid;
	private Integer clienteid;
	private BigDecimal igv;
	private Date fecharegistro;
	private List<VentaItem> items;
}
