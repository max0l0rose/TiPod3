package com.osdb.octipod.jwt;

//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class MyAuthFilter extends UsernamePasswordAuthenticationFilter {
//
//	@Override
//	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//																					throws AuthenticationException
//	{
////		//String dbValue = request.getParameter("dbParam");
////		//request.getSession().setAttribute("dbValue", dbValue);
////		System.out.println("attempting to authentificate");
////		while (request.getAttributeNames().hasMoreElements()) {
////			String e = (String) request.getAttributeNames().nextElement();
////			System.out.println("param name : " + e + " and param value : " + request.getAttribute(e));
////		}
//
//		String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
//		if (token != null && jwtTokenProvider.validateToken(token)) {
//			Authentication auth = jwtTokenProvider.getAuthentication(token);
//
//			// twice ??
//			if (auth != null) {
//				SecurityContextHolder.getContext().setAuthentication(auth);
//			}
//		}
//
//		return super.attemptAuthentication(request, response);
//	}
//
//}
