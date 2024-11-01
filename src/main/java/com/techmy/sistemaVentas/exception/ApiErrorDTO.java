package com.techmy.sistemaVentas.exception;

public record ApiErrorDTO(
		String message,
		String backendMessage,
		String method,
		String url) {

	
}
