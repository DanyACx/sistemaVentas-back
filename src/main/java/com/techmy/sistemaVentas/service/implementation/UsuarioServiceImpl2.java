package com.techmy.sistemaVentas.service.implementation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmy.sistemaVentas.persistence.entity.Persona;
import com.techmy.sistemaVentas.persistence.entity.Proveedor;
import com.techmy.sistemaVentas.persistence.entity.Role;
import com.techmy.sistemaVentas.persistence.entity.Trabajador;
import com.techmy.sistemaVentas.persistence.entity.UserAuth;
import com.techmy.sistemaVentas.presentation.dto.PersonaDTO;
import com.techmy.sistemaVentas.presentation.dto.ProveedorDTO;
import com.techmy.sistemaVentas.presentation.dto.RoleDTO;
import com.techmy.sistemaVentas.presentation.dto.TrabajadorDTO;
import com.techmy.sistemaVentas.presentation.dto.UserAuthDTO;
import com.techmy.sistemaVentas.service.interfaces.IUsuarioService;
import com.techmy.sistemaVentas.util.mapper.UserMapper;

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

	@Override
	public int insertarProveedor(ProveedorDTO proveedorDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Proveedor> getListaProveedores() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertarTrabajador(TrabajadorDTO trabajadorDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Trabajador> getListaTrabajadores() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Persona getPersonaByID(Integer personaid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserAuth> getListUserWithRoles() {
		// TODO Auto-generated method stub
		return null;
	}
}
