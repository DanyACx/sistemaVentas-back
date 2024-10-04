package com.techmy.sistemaVentas.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmy.sistemaVentas.persistence.entity.Almacen;
import com.techmy.sistemaVentas.persistence.entity.Articulo;
import com.techmy.sistemaVentas.persistence.entity.Categoria;
import com.techmy.sistemaVentas.persistence.entity.Ingreso;
import com.techmy.sistemaVentas.persistence.entity.TarifaVenta;
import com.techmy.sistemaVentas.presentation.dto.CategoriaDTO;
import com.techmy.sistemaVentas.presentation.dto.IngresoDTO;
import com.techmy.sistemaVentas.presentation.dto.TarifaVentaDTO;
import com.techmy.sistemaVentas.service.interfaces.ICategoriaService;
import com.techmy.sistemaVentas.util.mapper.CategoriaMapper;

import jakarta.transaction.Transactional;

@Service
public class CategoriaServiceImpl implements ICategoriaService{

	@Autowired
	private CategoriaMapper mapper;

	@Override
	public int insertarCategoria(CategoriaDTO categoriaDTO) {
		
		Categoria categoria = Categoria.builder()
				.nombre(categoriaDTO.getNombre())
				.descripcion(categoriaDTO.getDescripcion())
				.build();
		
		return mapper.insertCategoria(categoria);
	}

	@Override
	public List<Categoria> getListaCategoria() {
		
		return mapper.getListaCategorias();
	}
	
	@Transactional
	@Override
	public void insertarIngreso(IngresoDTO ingresoDTO) {
		
		Ingreso ingreso = Ingreso.builder()
				.tipocomprobante(ingresoDTO.getTipocomprobante())
				.seriecomprobante(ingresoDTO.getSeriecomprobante())
				.impuesto(ingresoDTO.getImpuesto())
				.descripcion(ingresoDTO.getDescripcion())
				.fechacompra(ingresoDTO.getFechacompra())
				.trabajadorid(ingresoDTO.getTrabajadorid())
				.proveedorid(ingresoDTO.getProveedorid())
				.build();
		
		mapper.insertarIngreso(ingreso);
		insertarArticulos(ingreso.getIdingreso(), ingresoDTO.getArticulos());
		insertarAlmacen(ingresoDTO.getArticulos(), ingresoDTO.getTrabajadorid());
	}
	
	public void insertarArticulos(Integer ingresoid, List<Articulo> articulos) {
		
		ArrayList<Articulo> articulosData = new ArrayList<Articulo>(); // Create an ArrayList object
		
		for(Articulo item : articulos) {
			item.setIngresoid(ingresoid);
			articulosData.add(item);
		}
		
		mapper.insertarArticulos(articulosData);
	}
	
	@Override
	public List<Ingreso> getListaIngreso() {
		
		return mapper.getListarIngresos();
	}

	@Override
	public void insertarAlmacen(List<Articulo> articulos, Integer trabajadorid) {
		
		ArrayList<Almacen> almacenData = new ArrayList<Almacen>();
		
		for(Articulo item : articulos) {
			Almacen aux = new Almacen();
			aux.setCodigoarticulo(item.getCodigoarticulo());
			aux.setCantidad(item.getCantidad());
			aux.setNombre(item.getNombre());
			aux.setTrabajadorid(trabajadorid);
			almacenData.add(aux);
		}
		
		mapper.insertarAlmacen(almacenData);
	}

	@Override
	public List<Almacen> getListaAlmacen() {
		// TODO Auto-generated method stub
		return mapper.getListaAlmacen();
	}

	@Override
	public void insertarTarifaVenta(TarifaVentaDTO tarifaVentaDTO) {
		
		TarifaVenta aux = TarifaVenta.builder()
				.precioventa(tarifaVentaDTO.getPrecioventa())
				.userauthid(tarifaVentaDTO.getUserauthid())
				.codigoarticulo(tarifaVentaDTO.getCodigoarticulo())
				.build();
			
		mapper.insertarTarifaVenta(aux);
	}
	
	@Override
	public List<TarifaVenta> getListaTarifaVenta() {
		// TODO Auto-generated method stub
		return mapper.getListaTarifaVenta();
	}
}
