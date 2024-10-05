package com.techmy.sistemaVentas.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.techmy.sistemaVentas.persistence.entity.Almacen;
import com.techmy.sistemaVentas.persistence.entity.Venta;
import com.techmy.sistemaVentas.persistence.entity.VentaItem;
import com.techmy.sistemaVentas.presentation.dto.VentaDTO;
import com.techmy.sistemaVentas.service.interfaces.IVentaService;
import com.techmy.sistemaVentas.util.mapper.VentaMapper;

import jakarta.transaction.Transactional;

@Service
public class VentaServiceImpl implements IVentaService{
	
	@Autowired
	private VentaMapper mapper;

	@Transactional
	@Override
	public void insertarVenta(VentaDTO ventaDTO) {
		
		try {
			Venta venta = Venta.builder()
					.trabajadorid(ventaDTO.getTrabajadorid())
					.clienteid(ventaDTO.getClienteid())
					.igv(ventaDTO.getIgv())
					.build();
			
			mapper.insertarVenta(venta);
			insertarItems(venta.getIdventa(), ventaDTO.getItems()); // aca se valida el stock por medio de un trigger
			insertarAlmacenItems(ventaDTO.getItems(), ventaDTO.getTrabajadorid());
			
		} catch (DataAccessException  e) {
			// Revisar la causa subyacente para ver si es un error de PostgreSQL
	        Throwable rootCause = e.getRootCause();
	        if (rootCause instanceof PSQLException) {
	            // Capturamos el mensaje del trigger lanzado desde PostgreSQL
	            String triggerMessage = rootCause.getMessage();
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, triggerMessage);
	        } else {
	            // Otros errores relacionados con la base de datos
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error en la base de datos", e);
	        }
	     
		}catch (Exception e) {
			
			// Si la excepción no es de stock, lanzar la excepción original
	        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al procesar la venta.");
		}
	}
	
	public void insertarItems(Integer ventaid, List<VentaItem> items) {
		
		ArrayList<VentaItem> itemsData = new ArrayList<VentaItem>(); // Create an ArrayList object
		
		for(VentaItem item : items) {
			item.setVentaid(ventaid);
			itemsData.add(item);
		}
		
		mapper.insertarItems(itemsData);
	}
	
	public void insertarAlmacenItems(List<VentaItem> items, Integer trabajadorid) {
		
		ArrayList<Almacen> almacenData = new ArrayList<Almacen>();
		
		for(VentaItem item : items) {
			Almacen aux = new Almacen();
			aux.setCodigoarticulo(item.getCodigoarticulo());
			aux.setCantidad(item.getCantidad()*(-1));
			aux.setTrabajadorid(trabajadorid);
			almacenData.add(aux);
		}
		
		mapper.insertarAlmacenItems(almacenData);
	}

	@Override
	public List<Venta> getListaVentas() {
		// TODO Auto-generated method stub
		return mapper.getListarVentas();
	}

}
