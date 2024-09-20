package com.techmy.sistemaVentas.util.mapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.techmy.sistemaVentas.persistence.entity.Permiso;
import com.techmy.sistemaVentas.persistence.entity.Persona;
import com.techmy.sistemaVentas.persistence.entity.Proveedor;
import com.techmy.sistemaVentas.persistence.entity.Role;
import com.techmy.sistemaVentas.persistence.entity.UserAuth;

@Mapper
public interface UserMapper {

	@Select({"SELECT id_persona, nombres, ap_paterno, ap_materno, tipo_persona, tipo_documento, num_documento,",
			 "direccion, telefono, email, fecha_registro ",
			 "  FROM dev_sysmain.persona ORDER BY id_persona DESC"})
	List<Persona> getListaPersonas();
	
	@Insert({
		"INSERT INTO dev_sysmain.persona (nombres, ap_paterno, ap_materno, tipo_persona, tipo_documento, num_documento, direccion, telefono, email) ",
		"   VALUES (#{nombres}, #{apellidopaterno}, #{apellidomaterno}, #{tipopersona}, #{tipodocumento}, #{numdocumento},",
		"   		#{direccion}, #{telefono}, #{email})"
	})
	int insertPersona(Persona persona);
	
	@Select({"SELECT * FROM dev_sysmain.persona WHERE id_persona = #{numdocumento}"})
	Optional<Persona> getPersonaPorNumDocumento(String numdocumento);
	
	//@Select({"SELECT id_user_auth, username, password, bloqueado, baja, fecha_registro, persona_id FROM dev_sysmain.user_auth ORDER BY id_user_auth DESC"})
	List<UserAuth> getListaUsuarios(); //
	
	@Select({"SELECT id_user_auth, username, password, bloqueado, baja, is_enabled, account_No_Expired, ",
			 " account_No_Locked, credential_No_Expired, persona_id ",
			 " FROM dev_sysmain.user_auth ",
			 " WHERE username = #{username}"})
	@Results(value = { @Result(property = "iduserauth", column = "id_user_auth"),
			@Result(property = "roles", column = "id_user_auth", javaType = Set.class, many = @Many(select = "com.techmy.sistemaVentas.util.mapper.UserMapper.getRolesFromUserAuth")) })
	Optional<UserAuth> getUserAuthPorUsername(String username);
	
	@Select({"SELECT r.id_role, r.role_nombre, r.role_descripcion, r.role_codigo  ",
			 "  FROM dev_sysmain.user_auth ua ",
			 "  INNER JOIN dev_sysmain.user_role ur ",
			 "  ON ua.id_user_auth = ur.id_user_auth ",
			 "  INNER JOIN dev_sysmain.role r ",
			 "  ON ur.id_role = r.id_role ",
			 "  WHERE ua.id_user_auth = #{iduserauth} "
		})
	@Results(value = { @Result(property = "idrole", column = "id_role"),
			@Result(property = "permisos", column = "id_role", javaType = Set.class, many = @Many(select = "com.techmy.sistemaVentas.util.mapper.UserMapper.getPermisosFromRole")) })
	Set<Role> getRolesFromUserAuth(Integer iduserauth);
	
	@Select({"SELECT p.id_permiso, p.permiso_nombre FROM dev_sysmain.role r  ",
		 "  INNER JOIN dev_sysmain.role_permiso rp ",
		 "  ON r.id_role = rp.id_role ",
		 "  INNER JOIN dev_sysmain.permiso p ",
		 "  ON rp.id_permiso = p.id_permiso ",
		 "  WHERE r.id_role = #{idrole} "
	})
	Set<Permiso> getPermisosFromRole(Integer idrole);
	
	@Select({"SELECT id_permiso, permiso_nombre FROM dev_sysmain.role ORDER BY id_role DESC"})
	Set<Permiso> getPermisos();
	
	@Insert({
		"INSERT INTO dev_sysmain.role (role_nombre, role_descripcion, role_codigo) ",
		"   VALUES (#{rolenombre}, #{roledescripcion}, #{rolecodigo})"
	})
	int insertRol(Role role);
	
	@Select({"SELECT * FROM dev_sysmain.role ORDER BY id_role DESC"})
	List<Role> getListaRoles();
	
	int insertarUserAuth(UserAuth userAuth);
	
	@Insert({
		"INSERT INTO dev_sysmain.user_role (id_user_auth, id_role) ",
		"   VALUES (#{iduserauth}, #{idrole})"
	})
	int agregarRolUserAuth(UserAuth userauth);
	
	List<Role> findRolesByNames(List<String> roleNames);
	
	@Insert({
		"INSERT INTO dev_sysmain.proveedor (ruc, razon_social, banco, telefono) ",
		"   VALUES (#{ruc}, #{razonsocial}, #{banco}, #{telefono})"
	})
	int insertProveedor(Proveedor proveedor);
	
	@Select({"SELECT id_proveedor, ruc, razon_social, banco, telefono, fecha_registro ",
		 "  FROM dev_sysmain.proveedor ORDER BY id_proveedor DESC"})
	List<Proveedor> getListaProveedores();
	
}
