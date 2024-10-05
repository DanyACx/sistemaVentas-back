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
import org.springframework.web.server.ResponseStatusException;

import com.techmy.sistemaVentas.persistence.entity.Venta;
import com.techmy.sistemaVentas.presentation.dto.VentaDTO;
import com.techmy.sistemaVentas.service.interfaces.IVentaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/venta")
@PreAuthorize("denyAll()")
public class VentaController {

private IVentaService ventaService;
	
	public VentaController() {}
	
	@Autowired
	public VentaController(@Qualifier("ventaServiceImpl") IVentaService ventaService) {
		this.ventaService = ventaService;
	}
	
	@PostMapping("/crearVenta")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('DEVELOPER')")
	public ResponseEntity<?> createVenta(@Valid @RequestBody VentaDTO ventaDTO) {
		
		try {
			ventaService.insertarVenta(ventaDTO);
			return new ResponseEntity<>("Venta se registro con exito", HttpStatus.CREATED);
			
		} catch (ResponseStatusException e) {
			// Maneja las excepciones específicas como la falta de stock
	        return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		} catch (Exception e) {
			// Maneja otras excepciones no previstas y devuelve un error 500 (INTERNAL_SERVER_ERROR)
	        System.err.println(e.getMessage());
	        return new ResponseEntity<>("Ocurrió un error inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/listarVentas")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('INVITADO') OR hasRole('USER') OR hasRole('DEVELOPER')")
	public List<Venta> listarVentas(){
		return ventaService.getListaVentas();
	}
}
