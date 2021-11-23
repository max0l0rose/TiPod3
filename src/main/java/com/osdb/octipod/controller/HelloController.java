package com.osdb.octipod.controller;

import com.osdb.octipod.dto.LoginDTO;
import com.osdb.octipod.jwt.JwtTokenUtils;
import com.osdb.octipod.model.HelloObject;
import com.osdb.octipod.model.SystemUser;
import com.osdb.octipod.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@RestController
@RequestMapping("/api/v1/public/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class HelloController {

	final AuthenticationManager authenticationManager;

	final JwtTokenUtils jwtTokenProvider;

	//final UserService userService;


	@RequestMapping(value = "/hello-world", method = {RequestMethod.PUT, RequestMethod.GET//, RequestMethod.OPTIONS
	}
	)
	@CrossOrigin(origins = {"http://domain21.com"}//, methods = {RequestMethod.DELETE, RequestMethod.POST}
	)
	HelloObject hello() {
		HelloObject helloObject = new HelloObject(1L, "My hello-world message");
		return helloObject;
	}


	@RequestMapping(value = "/sign-in", method = {RequestMethod.POST//, RequestMethod.OPTIONS
	}
	)
	ResponseEntity<String> login(
//			//@RequestParam
			 String username
//			, //@RequestBody
					  , String password
			, LoginDTO loginDTO1
			//, @Autowired AuthenticationManager authenticationManager
			, HttpServletResponse httpServletResponse
//			, HttpServletRequest httpServletRequest
	) {
		LoginDTO loginDTO = new LoginDTO(username, password);
		log.info("login: " + loginDTO.getUsername() + " " + loginDTO.getPassword());

		UsernamePasswordAuthenticationToken token =
				new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
		Authentication authentication = authenticationManager.authenticate(token);
		log.info("authenticated: " + loginDTO.getUsername() + " " + loginDTO.getPassword());

		SystemUser systemUser = (SystemUser)authentication.getPrincipal(); //userService.findByEmail(loginDTO.getUsername()).get();

		// ---Auth passed---
		String jwtToken = jwtTokenProvider.createToken(loginDTO.getUsername(), Arrays.asList(systemUser.getRole()));

		httpServletResponse.addCookie(new Cookie(HttpHeaders.AUTHORIZATION, "Bearer_" + jwtToken));

		return ResponseEntity.ok("Logged in...");
	}


	@PostMapping(value = "/sign-out")
	ResponseEntity<String> logout(
			HttpServletResponse httpServletResponse
			//, HttpServletRequest httpServletRequest
	) //throws ServletException
	{
		//httpServletRequest.logout();
		//SecurityContextHolder.clearContext();

		Cookie cookie = new Cookie(HttpHeaders.AUTHORIZATION, null);
		cookie.setMaxAge(0);
		httpServletResponse.addCookie(cookie);

		return ResponseEntity.ok("Logged out...");
	}



//	@ExceptionHandler({
////			Exception.class
//			JwtAuthenticationException.class
////			//,AccessDeniedException.class
//	})
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<Object> handleAccessDeniedException(
			Exception ex, WebRequest request) {
		return new ResponseEntity<>(
				"HelloController: AuthenticationException... " + ex.getMessage(),
				new HttpHeaders(), HttpStatus.FORBIDDEN);
	}
}


//	@RequestMapping( !!!!!!!!!!!!!!!!!!!!!!!!!
//			value = { "/api/pojo/edit" },
//			method = RequestMethod.POST,
//			produces = "application/json",
//			consumes = ["application/json"])
//	@ResponseBody
//	public Boolean editWinner( @RequestBody Pojo pojo) {

