package com.techmy.sistemaVentas.util.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.techmy.sistemaVentas.persistence.entity.Almacen;
import com.techmy.sistemaVentas.persistence.entity.Venta;
import com.techmy.sistemaVentas.persistence.entity.VentaItem;

@Mapper
public interface VentaMapper {

	@Select({"SELECT id_venta, codigo_venta, estado_venta, trabajador_id, cliente_id, igv, fecha_registro ",
	 	" FROM dev_sysmain.venta ORDER BY id_venta DESC"})
	@Results(value = { @Result(property = "idventa", column = "id_venta"),
	@Result(property = "items", column = "id_venta", javaType = List.class, many = @Many(select = "com.techmy.sistemaVentas.util.mapper.VentaMapper.getItemsFromVenta")) })
	List<Venta> getListarVentas();
	
	@Select({"SELECT id_venta_item, codigo_articulo, cantidad, precio_unitario, venta_id  ",
		 "  FROM dev_sysmain.venta_item ",
		 "  WHERE venta_id = #{idventa} "
	})
	List<VentaItem> getItemsFromVenta(Integer idventa);
	
	void insertarVenta(Venta venta);
	
	@Insert({
		"<script>",
		"INSERT INTO dev_sysmain.venta_item(",
	    "             codigo_articulo,",
	    "             cantidad,",
	    "             precio_unitario,",
		"             venta_id)",
		"     VALUES ",
		"   <foreach collection='list' item='item' separator=','>",
		"             (#{item.codigoarticulo},",
		"             #{item.cantidad},",
		"             #{item.preciounitario},",
		"             #{item.ventaid})",
		"   </foreach>",
		"</script>"
	})
	void insertarItems(@Param("list") List<VentaItem> data);
	
	@Insert({
		"<script>",
		"INSERT INTO dev_sysmain.almacen(",
	    "             codigo_articulo,",
	    "             cantidad,",
		"             trabajador_id)",
		"     VALUES ",
		"   <foreach collection='list' item='item' separator=','>",
		"             (#{item.codigoarticulo},",
		"             #{item.cantidad},",
		"             #{item.trabajadorid})",
		"   </foreach>",
		"</script>"
	})
	void insertarAlmacenItems(@Param("list") List<Almacen> data);
	
	@Select({"SELECT precio_venta FROM dev_sysmain.tarifa_venta WHERE codigo_articulo = #{codigoarticulo} AND estado = 1 "
	})
	BigDecimal getPrecioItem(String codigoarticulo);
}
