package com.osdb.octipod.controller;

import io.jsonwebtoken.JwtException;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

//@ControllerAdvice
@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler
{
	//@SuppressWarnings("unchecked")
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
	                                                                   HttpStatus status, WebRequest request)
	{
//		ResponseEntity<?> responseEntity;
//		if (!status.isError()) {
//			responseEntity = handleStatusException(ex, status, request);
//		} else if (INTERNAL_SERVER_ERROR.equals(status)) {
//			request.setAttribute("javax.servlet.error.exception", ex, 0);
//			responseEntity = handleEveryException(ex, request);
//		} else {
//			responseEntity = handleEveryException(ex, request);
//		}
		//return super.handleExceptionInternal(ex, body, headers, status, request);
		return ResponseEntity.status(status).body("RestResponseEntityExceptionHandler: ex: " + ex.getMessage() + ", body: " + body);
	}



	@ExceptionHandler({
//			Exception.class
			JwtException.class
//			//,AccessDeniedException.class
	})
	//@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ResponseEntity<Object> handleAccessDeniedException(
			Exception ex, WebRequest request) {
		return new ResponseEntity<Object>(
				"JwtAuthenticationException. Access denied message here",
				new HttpHeaders(), HttpStatus.FORBIDDEN);
	}

}


