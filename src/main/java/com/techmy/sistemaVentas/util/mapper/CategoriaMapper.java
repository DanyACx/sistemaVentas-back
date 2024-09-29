package com.techmy.sistemaVentas.util.mapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.techmy.sistemaVentas.persistence.entity.Articulo;
import com.techmy.sistemaVentas.persistence.entity.Categoria;
import com.techmy.sistemaVentas.persistence.entity.Ingreso;

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

}
