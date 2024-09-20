package com.techmy.sistemaVentas.service.interfaces;

import java.util.List;
import java.util.Map;

import com.techmy.sistemaVentas.persistence.entity.Persona;
import com.techmy.sistemaVentas.persistence.entity.Proveedor;
import com.techmy.sistemaVentas.persistence.entity.Role;
import com.techmy.sistemaVentas.presentation.dto.PersonaDTO;
import com.techmy.sistemaVentas.presentation.dto.ProveedorDTO;
import com.techmy.sistemaVentas.presentation.dto.RoleDTO;
import com.techmy.sistemaVentas.presentation.dto.UserAuthDTO;

public interface IUsuarioService {

	List<Persona> getPersonas();
	int insertarPersona(PersonaDTO personaDTO);
	List<Role> getListarRoles();
	int insertarRol(RoleDTO roleDTO);
	void insertarUserAuth(UserAuthDTO userAuthDTO);
	List<Map<String, Object>> getListarUsuarios();
	int insertarProveedor(ProveedorDTO proveedorDTO);
	List<Proveedor> getListaProveedores();
}
