package com.techmy.sistemaVentas.presentation.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.techmy.sistemaVentas.persistence.entity.VentaItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaDTO {

	private Integer idventa;
	private String codigoventa;
	private Integer estadoventa;
	private Integer trabajadorid;
	private Integer clienteid;
	private BigDecimal igv;
	private Date fecharegistro;
	private List<VentaItem> items;
}
