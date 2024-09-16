package com.techmy.sistemaVentas.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolePermiso {

	private Integer idrole;
	private Integer idpermiso;
}
