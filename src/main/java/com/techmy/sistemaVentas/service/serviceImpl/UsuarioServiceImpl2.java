package com.techmy.sistemaVentas.service.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmy.sistemaVentas.dao.UserMapper;
import com.techmy.sistemaVentas.dto.PersonaDTO;
import com.techmy.sistemaVentas.dto.RoleDTO;
import com.techmy.sistemaVentas.dto.UserAuthDTO;
import com.techmy.sistemaVentas.models.Persona;
import com.techmy.sistemaVentas.models.Role;
import com.techmy.sistemaVentas.service.IUsuarioService;

@Service
public class UsuarioServiceImpl2 implements IUsuarioService{

	@Autowired
	private UserMapper mapper;

	@Override
	public List<Persona> getPersonas() {
		
		return mapper.getListaPersonas();
	}

	@Override
	public int insertarPersona(PersonaDTO personaDTO) {
		
		Persona persona = Persona.builder()
				.nombres(personaDTO.getNombres())
				.appaterno(personaDTO.getAppaterno())
				.apmaterno(personaDTO.getApmaterno())
				.tipopersona(personaDTO.getTipopersona())
				.tipodocumento(personaDTO.getTipodocumento())
				.numdocumento(personaDTO.getNumdocumento())
				.direccion(personaDTO.getDireccion())
				.telefono(personaDTO.getTelefono())
				.email(personaDTO.getEmail())
				.build();
		
		return mapper.insertPersona(persona);
	}
	
	@Override
	public List<Role> getListarRoles() {
		return mapper.getListaRoles();
	}
	
	@Override
	public int insertarRol(RoleDTO roleDTO) {
		
		Role rol = Role.builder()
				.rolenombre(roleDTO.getRolenombre())
				.roledescripcion(roleDTO.getRoledescripcion())
				.rolecodigo(roleDTO.getRolecodigo())
				.build();
		
		return mapper.insertRol(rol);
	}

	@Override
	public void insertarUserAuth(UserAuthDTO userAuthDTO) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public List<Map<String, Object>> getListarUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}
}
