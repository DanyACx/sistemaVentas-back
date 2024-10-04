package com.techmy.sistemaVentas.util.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.techmy.sistemaVentas.persistence.entity.Almacen;
import com.techmy.sistemaVentas.persistence.entity.Articulo;
import com.techmy.sistemaVentas.persistence.entity.Categoria;
import com.techmy.sistemaVentas.persistence.entity.Ingreso;
import com.techmy.sistemaVentas.persistence.entity.TarifaVenta;

@Mapper
public interface CategoriaMapper {

	@Select({"SELECT id_categoria, nombre, descripcion, estado, fecha_registro ",
		 "  FROM dev_sysmain.categoria ORDER BY id_categoria DESC"})
	List<Categoria> getListaCategorias();

	@Insert({
		"INSERT INTO dev_sysmain.categoria (nombre, descripcion) ",
		"   VALUES (#{nombre}, #{descripcion})"
	})
	int insertCategoria(Categoria categoria);
	
	int insertarIngreso(Ingreso ingreso);
	
	@Insert({
		"<script>",
		"INSERT INTO dev_sysmain.articulo(",
	    "             codigo_serial,",
	    "             nombre,",
	    "             descripcion,",
		"             precio_unidad,",
		"             cantidad,",
		"             estado_articulo,",
		"             codigo_articulo,",
		"             categoria_id,",
		"             ingreso_id)",
		"     VALUES ",
		"   <foreach collection='list' item='item' separator=','>",
		"             (#{item.codigoserial},",
		"             #{item.nombre},",
		"             #{item.descripcion},",
		"             #{item.preciounidad},",
		"             #{item.cantidad,jdbcType=VARCHAR},",
		"             #{item.estadoarticulo,jdbcType=VARCHAR},",
		"             #{item.codigoarticulo},",
		"             #{item.categoriaid},",
		"             #{item.ingresoid})",
		"   </foreach>",
		"</script>"
	})
	void insertarArticulos(@Param("list") List<Articulo> data);
	
	@Select({"SELECT id_ingreso, tipo_comprobante, serie_comprobante, impuesto, descripcion, fecha_compra, trabajador_id, proveedor_id ",
		 " FROM dev_sysmain.ingreso ORDER BY id_ingreso DESC"})
	@Results(value = { @Result(property = "idingreso", column = "id_ingreso"),
		@Result(property = "articulos", column = "id_ingreso", javaType = List.class, many = @Many(select = "com.techmy.sistemaVentas.util.mapper.CategoriaMapper.getArticulosFromIngreso")) })
	List<Ingreso> getListarIngresos();

	@Select({"SELECT id_articulo, codigo_serial, nombre, descripcion, precio_unidad, cantidad, estado_articulo, codigo_articulo, categoria_id, ingreso_id  ",
		 "  FROM dev_sysmain.articulo ",
		 "  WHERE ingreso_id = #{idingreso} "
	})
	List<Articulo> getArticulosFromIngreso(Integer idingreso);
	
	@Insert({
		"<script>",
		"INSERT INTO dev_sysmain.almacen(",
	    "             codigo_articulo,",
	    "             cantidad,",
	    "             nombre,",
		"             trabajador_id)",
		"     VALUES ",
		"   <foreach collection='list' item='item' separator=','>",
		"             (#{item.codigoarticulo},",
		"             #{item.cantidad},",
		"             #{item.nombre},",
		"             #{item.trabajadorid})",
		"   </foreach>",
		"</script>"
	})
	void insertarAlmacen(@Param("list") List<Almacen> data);
	
	@Select({"SELECT id_almacen, codigo_articulo, cantidad, nombre, trabajador_id, fecha_registro  ",
		 "  FROM dev_sysmain.almacen ORDER BY id_almacen DESC"
	})
	List<Almacen> getListaAlmacen();
	
	@Insert({
		"INSERT INTO dev_sysmain.tarifa_venta (precio_venta, user_auth_id, codigo_articulo) ",
		"   VALUES (#{precioventa}, #{userauthid}, #{codigoarticulo})"
	})
	void insertarTarifaVenta(TarifaVenta tarifaVenta);
	
	@Select({"SELECT id_tarifa_venta, precio_venta, estado, user_auth_id, codigo_articulo, fecha_registro  ",
		 "  FROM dev_sysmain.tarifa_venta ORDER BY id_tarifa_venta DESC"
	})
	List<TarifaVenta> getListaTarifaVenta();

}
