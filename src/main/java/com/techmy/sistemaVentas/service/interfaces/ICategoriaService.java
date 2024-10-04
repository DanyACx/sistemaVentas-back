package com.techmy.sistemaVentas.service.interfaces;

import java.util.List;

import com.techmy.sistemaVentas.persistence.entity.Almacen;
import com.techmy.sistemaVentas.persistence.entity.Articulo;
import com.techmy.sistemaVentas.persistence.entity.Categoria;
import com.techmy.sistemaVentas.persistence.entity.Ingreso;
import com.techmy.sistemaVentas.persistence.entity.TarifaVenta;
import com.techmy.sistemaVentas.presentation.dto.CategoriaDTO;
import com.techmy.sistemaVentas.presentation.dto.IngresoDTO;
import com.techmy.sistemaVentas.presentation.dto.TarifaVentaDTO;

public interface ICategoriaService {

	int insertarCategoria(CategoriaDTO categoriaDTO);
	List<Categoria> getListaCategoria();
	void insertarIngreso(IngresoDTO ingresoDTO);
	List<Ingreso> getListaIngreso();
	void insertarAlmacen(List<Articulo> articulos, Integer trabajadorid);
	List<Almacen> getListaAlmacen();
	void insertarTarifaVenta(TarifaVentaDTO tarifaVentaDTO);
	List<TarifaVenta> getListaTarifaVenta();
}
