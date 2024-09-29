package com.techmy.sistemaVentas.presentation.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrabajadorDTO {

	private String codigo;
	private String activotrabajador;
	private Integer fechacese;
	private Integer personaid;
	private Date fecharegistro;
}
