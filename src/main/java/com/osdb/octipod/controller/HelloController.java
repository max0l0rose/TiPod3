package com.osdb.octipod.controller;

import com.osdb.octipod.jwt.JwtTokenUtils;
import com.osdb.octipod.model.HelloObject;
import com.osdb.octipod.model.SystemUser;
import com.osdb.octipod.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@RestController
@RequestMapping("/api/v1/public/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HelloController {

	final AuthenticationManager authenticationManager;

	final JwtTokenUtils jwtTokenProvider;

	final  UserService userService;


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
	void login(
			@RequestParam("email") String email
			, @RequestParam("password") String password
			, HttpServletResponse httpServletResponse
	) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
		Authentication authentication =
		authenticationManager.authenticate(token);

		SystemUser systemUser = userService.findByEmail(email).get();
		// ---Auth passed---
		String jwtToken = jwtTokenProvider.createToken(email, Arrays.asList(systemUser.getRole()));

		httpServletResponse.addCookie(new Cookie("Authorization", "Bearer_" + jwtToken));

		//return new ResponseEntity<String>(id,headers,HttpStatus.OK);
	}

}


//	@RequestMapping(
//			value = { "/api/pojo/edit" },
//			method = RequestMethod.POST,
//			produces = "application/json",
//			consumes = ["application/json"])
//	@ResponseBody
//	public Boolean editWinner( @RequestBody Pojo pojo) {

