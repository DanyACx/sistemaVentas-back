package com.techmy.sistemaVentas.presentation.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlmacenDTO {

	private Integer idalmacen;
	private String codigoarticulo;
	private Integer cantidad;
	private String nombre;
	private Integer trabajadorid;
	private Date fecharegistro;
}
