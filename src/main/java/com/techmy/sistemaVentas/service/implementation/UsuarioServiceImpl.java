package com.techmy.sistemaVentas.service.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techmy.sistemaVentas.persistence.entity.Persona;
import com.techmy.sistemaVentas.persistence.entity.Proveedor;
import com.techmy.sistemaVentas.persistence.entity.Role;
import com.techmy.sistemaVentas.persistence.entity.UserAuth;
import com.techmy.sistemaVentas.presentation.dto.PersonaDTO;
import com.techmy.sistemaVentas.presentation.dto.ProveedorDTO;
import com.techmy.sistemaVentas.presentation.dto.RoleDTO;
import com.techmy.sistemaVentas.presentation.dto.UserAuthDTO;
import com.techmy.sistemaVentas.service.interfaces.IUsuarioService;
import com.techmy.sistemaVentas.util.mapper.UserMapper;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

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
		UserAuth userAuth = UserAuth.builder()
				.username(userAuthDTO.getUsername())
				.password(passwordEncoder.encode(userAuthDTO.getPassword()))
				.personaid(userAuthDTO.getPersonaid())
				.build();
		
		 mapper.insertarUserAuth(userAuth);
		 agregarRoles(userAuth.getIduserauth(), userAuthDTO.getRoles());
	}
	
	public void agregarRoles(Integer iduserauth, Set<Role> roles) {
		
		UserAuth userauth = new UserAuth();
		
		for(Role rol : roles) {
			userauth.setIduserauth(iduserauth);
			userauth.setIdrole(rol.getIdrole());
			mapper.agregarRolUserAuth(userauth);
		}
	}

	@Override
	public List<Map<String, Object>> getListarUsuarios() {
		List<UserAuth> usuarios = mapper.getListaUsuarios();
		List<Map<String, Object>> resultado = new ArrayList<>();
		
		 for (UserAuth usuario : usuarios) {
	            Map<String, Object> mapaUsuario = new HashMap<>();
	            mapaUsuario.put("iduserauth", usuario.getIduserauth());
	            mapaUsuario.put("username", usuario.getUsername());
	            mapaUsuario.put("bloqueado", usuario.getBloqueado());
	            mapaUsuario.put("baja", usuario.getBaja());
	            mapaUsuario.put("idpersona", usuario.getPersonaid());
	            // Agregar otros campos que sean relevantes
	            resultado.add(mapaUsuario);
	        }

	        return resultado;
	}
	
	public int insertarProveedor(ProveedorDTO proveedorDTO) {
		Proveedor proveedor = Proveedor.builder()
				.ruc(proveedorDTO.getRuc())
				.razonsocial(proveedorDTO.getRazonsocial())
				.banco(proveedorDTO.getBanco())
				.telefono(proveedorDTO.getTelefono())
				.build();
		
		 return mapper.insertProveedor(proveedor);
	}
	
	@Override
	public List<Proveedor> getListaProveedores() {
		
		return mapper.getListaProveedores();
	}

}
