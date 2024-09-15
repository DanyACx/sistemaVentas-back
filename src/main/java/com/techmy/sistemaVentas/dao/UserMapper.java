package com.techmy.sistemaVentas.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.techmy.sistemaVentas.models.Persona;
import com.techmy.sistemaVentas.models.Role;
import com.techmy.sistemaVentas.models.UserAuth;

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
	
	@Select({"SELECT * FROM dev_sysmain.user_auth WHERE username = #{username}"})
	Optional<UserAuth> getUserPorUsername(String username);
	
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
}
