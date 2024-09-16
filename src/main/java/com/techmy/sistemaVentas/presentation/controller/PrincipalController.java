package com.techmy.sistemaVentas.presentation.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techmy.sistemaVentas.persistence.entity.Persona;
import com.techmy.sistemaVentas.persistence.entity.Role;
import com.techmy.sistemaVentas.presentation.dto.PersonaDTO;
import com.techmy.sistemaVentas.presentation.dto.RoleDTO;
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

	@GetMapping("/hola")
	public String hello() {
		return "holaaa sin seguridad";
	}

	@GetMapping("/holaSeguro")
	public String helloSeguro() {
		return "holaaa con seguridad";
	}

	@PostMapping("/crearPersona")
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
	@PreAuthorize("hasAuthority('READ')")
	public List<Persona> listarClientes(){
		return usuarioService.getPersonas();
	}

	@PostMapping("/crearRol")
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
	@PreAuthorize("hasAuthority('READ')")
	public List<Role> listarRoles(){
		return usuarioService.getListarRoles();
	}
	
	@PostMapping("/crearUserAuth")
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
	@PreAuthorize("hasAuthority('READ')")
	public ResponseEntity<List<Map<String, Object>>> listarUsuarios(){
		 List<Map<String, Object>> usuarios = usuarioService.getListarUsuarios();
	        return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}

}
