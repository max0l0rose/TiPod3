package com.osdb.octipod.jwt2;

//AUTHOR
//		Ioram Gordadze

import com.osdb.octipod.jwt.JwtTokenUtils;
import com.osdb.octipod.repo.UserRepository;
import io.jsonwebtoken.JwtException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtTokenFilter2 extends OncePerRequestFilter
									//UsernamePasswordAuthenticationFilter;
									//BasicAuthenticationFilter;
{
	final JwtTokenUtils jwtTokenUtils;
	final UserRepository userRepo;

//	public JwtTokenFilter2(JwtTokenUtil jwtTokenUtil,
//	                      UserRepository userRepo) {
//		this.jwtTokenUtil = jwtTokenUtil;
//		this.userRepo = userRepo;
//	}

//	@Bean(DispatcherServlet.HANDLER_EXCEPTION_RESOLVER_BEAN_NAME)
//	HandlerExceptionResolver customExceptionResolver (ApplicationContext ac) {
//
//		ExceptionHandlerExceptionResolver e = new ExceptionHandlerExceptionResolver();
//		e.setApplicationContext(ac);
//		e.afterPropertiesSet();
//
////		SimpleMappingExceptionResolver s = new SimpleMappingExceptionResolver();
////		s.setDefaultErrorView("default-error");
////		s.setDefaultStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//
//		HandlerExceptionResolverComposite c = new HandlerExceptionResolverComposite();
//		c.setExceptionResolvers(Arrays.asList(e));
//		return c;
//	}


//	//@Qualifier("handlerExceptionResolver")
//	final HandlerExceptionResolver resolver;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
	                                HttpServletResponse response,
	                                FilterChain chain) throws ServletException, IOException
	{
//		// Get authorization header and validate
//		final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
//		if (isEmpty(header) || !header.startsWith("Bearer ")) {
//			chain.doFilter(request, response);
//			return;
//		}

		String token = jwtTokenUtils.resolveToken(request);
		if (token == null) {
			chain.doFilter(request, response);
			return;
		}

		// Get jwt token and validate
		//final String token = header.split(" ")[1].trim();
		try {
//			if (!jwtTokenUtils.validateToken(token))
//			{
//
////			    response.setStatus(HttpStatus.NOT_FOUND.value());
//				//response.getWriter().write("{qqqqq : 'wwwww'}");
//
//				chain.doFilter(request, response);
//				return;
//			}

			// Get user identity and set it on the spring security context
			String username = jwtTokenUtils.getUsername(token);
			UserDetails userDetails = userRepo.findByEmail(username).orElse(null);

			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails,
					null,
					userDetails == null ? Collections.EMPTY_LIST : userDetails.getAuthorities() //List.of()
			);

			authenticationToken.setDetails(
					new WebAuthenticationDetailsSource()
							.buildDetails(request)
			);

			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		} catch (JwtException e) {
			//SecurityContextHolder.clearContext();
			//resolver.resolveException(request, response, null, new JwtAuthenticationException("JWT token is expired or invalid.."));
			e.printStackTrace();
		}
		chain.doFilter(request, response);
	}




//	@ExceptionHandler({
////			Exception.class
//			JwtAuthenticationException.class
////			//,AccessDeniedException.class
//	})
//	//@ExceptionHandler(AuthenticationException.class)
//	@ResponseStatus(HttpStatus.FORBIDDEN)
//	public ResponseEntity<Object> handleAccessDeniedException(
//			Exception ex, WebRequest request) {
//		return new ResponseEntity<Object>(
//				"JwtAuthenticationException. Access denied message here",
//				new HttpHeaders(), HttpStatus.FORBIDDEN);
//	}

}
