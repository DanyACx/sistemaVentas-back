package com.techmy.sistemaVentas.service;

import java.util.List;
import java.util.Map;

import com.techmy.sistemaVentas.dto.PersonaDTO;
import com.techmy.sistemaVentas.dto.RoleDTO;
import com.techmy.sistemaVentas.dto.UserAuthDTO;
import com.techmy.sistemaVentas.models.Persona;
import com.techmy.sistemaVentas.models.Role;

public interface IUsuarioService {

	List<Persona> getPersonas();
	int insertarPersona(PersonaDTO personaDTO);
	List<Role> getListarRoles();
	int insertarRol(RoleDTO roleDTO);
	void insertarUserAuth(UserAuthDTO userAuthDTO);
	List<Map<String, Object>> getListarUsuarios();
}
