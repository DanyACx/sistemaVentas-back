package com.techmy.sistemaVentas.service.interfaces;

import java.util.List;

import com.techmy.sistemaVentas.persistence.entity.Venta;
import com.techmy.sistemaVentas.presentation.dto.VentaDTO;

public interface IVentaService {

	void insertarVenta(VentaDTO ventaDTO);
	List<Venta> getListaVentas();
}
