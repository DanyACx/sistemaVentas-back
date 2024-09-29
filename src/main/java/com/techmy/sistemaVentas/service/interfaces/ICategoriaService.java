package com.techmy.sistemaVentas.service.interfaces;

import java.util.List;

import com.techmy.sistemaVentas.persistence.entity.Categoria;
import com.techmy.sistemaVentas.persistence.entity.Ingreso;
import com.techmy.sistemaVentas.presentation.dto.CategoriaDTO;
import com.techmy.sistemaVentas.presentation.dto.IngresoDTO;

public interface ICategoriaService {

	int insertarCategoria(CategoriaDTO categoriaDTO);
	List<Categoria> getListaCategoria();
	void insertarIngreso(IngresoDTO ingresoDTO);
	List<Ingreso> getListaIngreso();
}
