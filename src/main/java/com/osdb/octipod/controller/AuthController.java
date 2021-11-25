package com.osdb.octipod.controller;

import com.osdb.octipod.dto.LoginDTO;
import com.osdb.octipod.dto.UserInfoDTO;
import com.osdb.octipod.jwt.JwtTokenUtils;
import com.osdb.octipod.model.SystemUser;
import com.osdb.octipod.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/public/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class AuthController {
	final AuthenticationManager authenticationManager;
	final JwtTokenUtils jwtTokenProvider;
	final UserService userService;

	// ============================================================================================
	@RequestMapping(value = "/sign-in", method = {RequestMethod.POST//, RequestMethod.OPTIONS
	})
	//Content-Type: application/x-www-form-urlencoded
	ResponseEntity<String> login(
			 String username
			, String password
			//, LoginDTO loginDTO1
			//, @Autowired AuthenticationManager authenticationManager // ??????????????????
			, HttpServletResponse httpServletResponse
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

		Cookie cookie = new Cookie(HttpHeaders.AUTHORIZATION, "Bearer_" + jwtToken);
		cookie.setPath("/");
		httpServletResponse.addCookie(cookie);

		return ResponseEntity.ok("Logged in: " + loginDTO);
	}


	// ============================================================================================
	@PostMapping(value = "/sign-out")
	ResponseEntity<String> logout(
			HttpServletResponse httpServletResponse
	) {
		//httpServletRequest.logout();
		//SecurityContextHolder.clearContext();

		Cookie cookie = new Cookie(HttpHeaders.AUTHORIZATION, null);
		cookie.setMaxAge(0);
		cookie.setPath("/"); // !!!!
		httpServletResponse.addCookie(cookie);

		return ResponseEntity.ok("Logged out...");
	}




	// ============================================================================================
	@GetMapping(value = "/user")
	ResponseEntity<UserInfoDTO> user(
			@RequestParam UUID id
	) {
		Optional<SystemUser> optionalSystemUser = userService.findById(id);
		if (optionalSystemUser.isPresent()) {
			UserInfoDTO userInfoDTO = UserInfoDTO.fromSystemUser(optionalSystemUser.get());
			return ResponseEntity.ok(userInfoDTO);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserInfoDTO());
	}




	// ============================================================================================
	// ============================================================================================
	// ============================================================================================
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

