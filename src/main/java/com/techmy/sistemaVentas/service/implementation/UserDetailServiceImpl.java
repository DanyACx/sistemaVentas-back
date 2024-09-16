package com.techmy.sistemaVentas.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techmy.sistemaVentas.persistence.entity.UserAuth;
import com.techmy.sistemaVentas.util.mapper.UserMapper;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserAuth userAuth = mapper.getUserAuthPorUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));
		
		List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
		
		userAuth.getRoles()
			.forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRolecodigo()))));
		
		userAuth.getRoles().stream()
			.flatMap(role -> role.getPermisos().stream())
			.forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getPermisonombre())));
		
		return new User(userAuth.getUsername(),
				userAuth.getPassword(),
				userAuth.isEnabled(),
				userAuth.isAccountNoExpired(),
				userAuth.isCredentialNoExpired(),
				userAuth.isAccountNoLocked(),
				authorityList);
	}

}
