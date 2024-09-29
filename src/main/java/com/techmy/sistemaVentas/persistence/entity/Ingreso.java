package com.techmy.sistemaVentas.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
public class Ingreso {
	
	@Id
	private Integer idingreso;
	@NotBlank
	private String tipocomprobante;
	@NotBlank
	private String seriecomprobante;
	@NotBlank
	private BigDecimal impuesto;
	@NotBlank
	private String descripcion;
	private Date fechacompra;
	private Integer trabajadorid;
	private Integer proveedorid;
	private List<Articulo> articulos;
	
	
}
