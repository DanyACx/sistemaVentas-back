package com.techmy.sistemaVentas.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techmy.sistemaVentas.configuration.jwt.JwtUtils;
import com.techmy.sistemaVentas.persistence.entity.UserAuth;
import com.techmy.sistemaVentas.presentation.dto.AuthLoginRequest;
import com.techmy.sistemaVentas.presentation.dto.AuthResponse;
import com.techmy.sistemaVentas.util.mapper.UserMapper;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserAuth userAuth = mapper.getUserAuthPorUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));
		
		List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
		
		userAuth.getRoles()
			.forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRolenombre())))); // considerar el "ROLE_"
		
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
	
	public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {
		String username = authLoginRequest.username();
		String password = authLoginRequest.password();
		
		Authentication authentication = this.authenticate(username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String accessToken = jwtUtils.createToken(authentication);
		
		AuthResponse authResponse = new AuthResponse(username, "user logueado con exito :)", accessToken, true);
		
		return authResponse;
	}
	
	public Authentication authenticate(String username, String password) {
		UserDetails userDetails = this.loadUserByUsername(username);
		
		if(userDetails == null) {
			throw new BadCredentialsException("username o password invalido :(");
		}
		
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("password invalido");
		}
		
		return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
	}
	
}
