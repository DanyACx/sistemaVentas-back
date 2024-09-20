package com.techmy.sistemaVentas.persistence.entity;

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
public class Proveedor {

	@Id
	private Integer idproveedor;
	@NotBlank
	private String ruc;
	@NotBlank
	private String razonsocial;
	private String banco;
	private String telefono;
	private Date fecharegistro;
}
