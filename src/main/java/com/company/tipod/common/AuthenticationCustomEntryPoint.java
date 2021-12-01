package com.company.tipod.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthenticationCustomEntryPoint implements AuthenticationEntryPoint {
	//private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

	// goes if errors only
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException {
		//logger.error(e.getLocalizedMessage(), e);
//		String message = RestResponse.builder()
//				.status(UNAUTHORIZED)
//				.error("Unauthenticated")
//				.message("Insufficient authentication details")
//				.path(request.getRequestURI())
//				.json();

//		response.sendError(
//				HttpServletResponse.SC_UNAUTHORIZED,
//				ex.getMessage()
//		);

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.TEXT_HTML_VALUE);
		response.getWriter().write("AuthenticationCustomEntryPoint: HttpStatus.UNAUTHORIZED... " + ex.getMessage());
	}
}
