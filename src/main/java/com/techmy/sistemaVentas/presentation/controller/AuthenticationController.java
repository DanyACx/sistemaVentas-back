package com.techmy.sistemaVentas.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techmy.sistemaVentas.presentation.dto.AuthLoginRequest;
import com.techmy.sistemaVentas.presentation.dto.AuthResponse;
import com.techmy.sistemaVentas.service.implementation.UserDetailServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@PreAuthorize("permitAll()")
public class AuthenticationController {

	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;
	
	/*@PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateUserRequest userRequest){
        return new ResponseEntity<>(this.userDetailServiceImpl.createUser(userRequest), HttpStatus.CREATED);
    }*/
	
	@PostMapping("/log-in")
	public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest) {
		return new ResponseEntity<>(this.userDetailServiceImpl.loginUser(userRequest), HttpStatus.OK);
	}
}
