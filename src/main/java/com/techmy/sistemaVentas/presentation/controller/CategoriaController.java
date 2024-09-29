package com.techmy.sistemaVentas.presentation.controller;

import java.util.List;

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

import com.techmy.sistemaVentas.persistence.entity.Categoria;
import com.techmy.sistemaVentas.persistence.entity.Ingreso;
import com.techmy.sistemaVentas.presentation.dto.CategoriaDTO;
import com.techmy.sistemaVentas.presentation.dto.IngresoDTO;
import com.techmy.sistemaVentas.service.interfaces.ICategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/categoria")
@PreAuthorize("denyAll()")
public class CategoriaController {

	private ICategoriaService categoriaService;
	
	public CategoriaController() {}
	
	@Autowired
	public CategoriaController(@Qualifier("categoriaServiceImpl") ICategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}
	
	@PostMapping("/crearCategoria")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('DEVELOPER')")
	public ResponseEntity<?> createCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {
		
		try {
			categoriaService.insertarCategoria(categoriaDTO);
			return new ResponseEntity<>("Se registro con exito", HttpStatus.CREATED);
		} catch (Exception e) {
			// Maneja la excepción y responde con un código específico
			System.err.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/listarCategorias")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('INVITADO') OR hasRole('USER') OR hasRole('DEVELOPER')")
	public List<Categoria> listarCategorias(){
		return categoriaService.getListaCategoria();
	}
	
	@PostMapping("/crearIngreso")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('DEVELOPER')")
	public ResponseEntity<?> createIngreso(@Valid @RequestBody IngresoDTO ingresoDTO) {
		
		try {
			categoriaService.insertarIngreso(ingresoDTO);
			return new ResponseEntity<>("Ingreso se registro con exito", HttpStatus.CREATED);
		} catch (Exception e) {
			// Maneja la excepción y responde con un código específico
			System.err.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/listarIngresos")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('INVITADO') OR hasRole('USER') OR hasRole('DEVELOPER')")
	public List<Ingreso> listarIngresos(){
		return categoriaService.getListaIngreso();
	}
	
}
