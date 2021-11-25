package com.osdb.octipod.common;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


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



	@ExceptionHandler({BusinessException.class})
	public ResponseEntity<Object> handleBusiness(
			Exception ex
	) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}



	@ExceptionHandler({
//			Exception.class
			JwtException.class
//			//,AccessDeniedException.class
			//AuthenticationException.class
	})
	//@ResponseStatus(HttpStatus.FORBIDDEN)
	public ResponseEntity<Object> handleAccessDeniedException(
			Exception ex, WebRequest request) {
		return new ResponseEntity<Object>(
				"JwtAuthenticationException. Access denied message here",
				new HttpHeaders(), HttpStatus.FORBIDDEN);
	}

}


