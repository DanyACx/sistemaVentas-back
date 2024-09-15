package com.techmy.sistemaVentas.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "role", schema = "dev_sysmain")
public class Role {

	@Id
	private Integer idrole;
	@NotBlank
	private String rolenombre;
	private String roledescripcion;
	@NotBlank
	private String rolecodigo;
}
