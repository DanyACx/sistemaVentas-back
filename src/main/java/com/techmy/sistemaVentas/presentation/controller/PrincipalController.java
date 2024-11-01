package com.techmy.sistemaVentas.presentation.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/principal")
@PreAuthorize("denyAll()")
public class PrincipalController {

	private IUsuarioService usuarioService; // private final IUsuarioService usuarioService;
	
	public PrincipalController () {}

	@Autowired
	public PrincipalController(@Qualifier("usuarioServiceImpl") IUsuarioService usuarioService) {
		this.usuarioService = usuarioService; // una vez que se asigna un valor a ese campo, no puede ser modificado
		// estás asegurando que su valor no cambiará después de la inicialización
	}

	@PostMapping("/crearPersona")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('DEVELOPER')")
	public ResponseEntity<?> createPersona(@Valid @RequestBody PersonaDTO personaDTO) {
		
		try {
			usuarioService.insertarPersona(personaDTO);
			return new ResponseEntity<>("Se registro con exito", HttpStatus.CREATED);
		} catch (Exception e) {
			// Maneja la excepción y responde con un código específico
			System.err.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/listarPersonas")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('INVITADO') OR hasRole('USER') OR hasRole('DEVELOPER')")
	public List<Persona> listarClientes(){
		return usuarioService.getPersonas();
	}

	@PostMapping("/crearRol")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('DEVELOPER')")
	public ResponseEntity<?> createRol(@Valid @RequestBody RoleDTO roleDTO) {
		
		try {
			usuarioService.insertarRol(roleDTO);
			return new ResponseEntity<>("Se registro con exito", HttpStatus.CREATED);
		} catch (Exception e) {
			// Maneja la excepción y responde con un código específico
			System.err.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/listarRoles")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('INVITADO') OR hasRole('USER') OR hasRole('DEVELOPER')")
	public List<Role> listarRoles(){
		return usuarioService.getListarRoles();
	}
	
	@PostMapping("/crearUserAuth")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('DEVELOPER')")
	public ResponseEntity<?> insertarUserAuth(@Valid @RequestBody UserAuthDTO userAuthDTO) {
		
		try {
			usuarioService.insertarUserAuth(userAuthDTO);
			return new ResponseEntity<>("Se registro con exito", HttpStatus.CREATED);
		} catch (Exception e) {
			// Maneja la excepción y responde con un código específico
			System.err.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/listarUsuarios")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('INVITADO') OR hasRole('USER') OR hasRole('DEVELOPER')") // @PreAuthorize("hasAuthority('READ')")
	public ResponseEntity<List<Map<String, Object>>> listarUsuarios(){
		 List<Map<String, Object>> usuarios = usuarioService.getListarUsuarios();
	        return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}
	
	@GetMapping("/listarUsuariosConRoles")
	@PreAuthorize("hasRole('DEVELOPER')") // @PreAuthorize("hasAuthority('READ')")
	public List<UserAuth> listUserWithRoles(){
		return usuarioService.getListUserWithRoles();
	}
	
	@PostMapping("/crearProveedor")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('DEVELOPER')")
	public ResponseEntity<?> createProveedor(@Valid @RequestBody ProveedorDTO proveedorDTO) {
		
		try {
			usuarioService.insertarProveedor(proveedorDTO);
			return new ResponseEntity<>("Se registro proveedor con exito", HttpStatus.CREATED);
		} catch (Exception e) {
			// Maneja la excepción y responde con un código específico
			System.err.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/listarProveedores")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('INVITADO') OR hasRole('USER') OR hasRole('DEVELOPER')") // @PreAuthorize("hasAuthority('READ')")
	public List<Proveedor> listarProveedores(){
		return usuarioService.getListaProveedores();
	}
	
	@PostMapping("/crearTrabajador")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('DEVELOPER')")
	public ResponseEntity<?> createTrabajador(@Valid @RequestBody TrabajadorDTO trabajadorDTO) {
		
		try {
			usuarioService.insertarTrabajador(trabajadorDTO);
			return new ResponseEntity<>("Se registro trabajador con exito", HttpStatus.CREATED);
		} catch (Exception e) {
			// Maneja la excepción y responde con un código específico
			System.err.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/listarTrabajadores")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('INVITADO') OR hasRole('USER') OR hasRole('DEVELOPER')") // @PreAuthorize("hasAuthority('READ')")
	public List<Trabajador> listarTrabajores(){
		return usuarioService.getListaTrabajadores();
	}
	
	@GetMapping("/buscarPersona/{personaid}")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('INVITADO') OR hasRole('USER') OR hasRole('DEVELOPER')")
	public Persona buscarPersona(@PathVariable("personaid") Integer personaid){
		return usuarioService.getPersonaByID(personaid);
	}

}
