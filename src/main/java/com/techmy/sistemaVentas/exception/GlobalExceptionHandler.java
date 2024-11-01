package com.techmy.sistemaVentas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorDTO> handlerGeneralExceptions(
			Exception exception, 
			HttpServletRequest request, 
			WebRequest webRequest){
		if(exception instanceof HttpClientErrorException) {
			return this.handlerHttpClientErrorException((HttpClientErrorException) exception, request, webRequest);
		}else if(exception instanceof AccessDeniedException) {
			return this.handlerAccessDeniedException((AccessDeniedException) exception, request, webRequest);
		} else if(exception instanceof AuthenticationCredentialsNotFoundException) {
			return this.handlerAuthenticationCredentialsNotFoundException((AuthenticationCredentialsNotFoundException) exception, request, webRequest);
		} else {
			return this.handlerGenericException(exception, request, webRequest);
		}
		
	}

	private ResponseEntity<ApiErrorDTO> handlerGenericException(
			Exception exception, 
			HttpServletRequest request,
			WebRequest webRequest) {
		
		ApiErrorDTO dto = new ApiErrorDTO("Error inesperado vuelve a intentarlo", 
				exception.getMessage(), 
				request.getMethod(), 
				request.getRequestURL().toString());
		
		return ResponseEntity.internalServerError().body(dto);
	}

	private ResponseEntity<ApiErrorDTO> handlerAuthenticationCredentialsNotFoundException(
			AuthenticationCredentialsNotFoundException exception, 
			HttpServletRequest request, 
			WebRequest webRequest) {
		
		ApiErrorDTO dto = new ApiErrorDTO("No tienes acceso a este recurso", 
				exception.getMessage(), 
				request.getMethod(), 
				request.getRequestURL().toString());
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(dto);
	}

	private ResponseEntity<ApiErrorDTO> handlerAccessDeniedException(
			AccessDeniedException exception,
			HttpServletRequest request, 
			WebRequest webRequest) {
		
		ApiErrorDTO dto = new ApiErrorDTO("No tienes acceso a este recurso", 
				exception.getMessage(), 
				request.getMethod(), 
				request.getRequestURL().toString());
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(dto);
		
	}

	private ResponseEntity<ApiErrorDTO> handlerHttpClientErrorException(
			HttpClientErrorException exception,
			HttpServletRequest request, 
			WebRequest webRequest) {
		
		String message = null;
		if(exception instanceof HttpClientErrorException.Forbidden) {
			message = "No tienes acceso a este recurso";
		} else if(exception instanceof HttpClientErrorException.Unauthorized) {
			message = "No haz iniciado sessión para acceder a este recurso";
		} else if(exception instanceof HttpClientErrorException.NotFound) {
			message = "Recurso no encontrado";
		} else if(exception instanceof HttpClientErrorException.Conflict) {
			message = "Conflicto en el procesamiento de la petición";
		} else {
			message = "Error inesperado al realizar consulta";
		}
		
		ApiErrorDTO dto = new ApiErrorDTO(
				message, 
				exception.getMessage(), 
				request.getMethod(), 
				request.getRequestURL().toString());
		
		return ResponseEntity.status(exception.getStatusCode()).body(dto);
		
	}
}
