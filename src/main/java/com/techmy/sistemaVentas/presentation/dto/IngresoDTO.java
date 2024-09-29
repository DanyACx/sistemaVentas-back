package com.techmy.sistemaVentas.presentation.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.techmy.sistemaVentas.persistence.entity.Articulo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngresoDTO {

	private Integer idingreso;
	private String tipocomprobante;
	private String seriecomprobante;
	private BigDecimal impuesto;
	private String descripcion;
	private Date fechacompra;
	private Integer trabajadorid;
	private Integer proveedorid;
	private List<Articulo> articulos;
}
