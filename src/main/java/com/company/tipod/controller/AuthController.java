package com.company.tipod.controller;

import com.company.tipod.dto.LoginDTO;
import com.company.tipod.dto.Step1DTO;
import com.company.tipod.dto.Step2DTO;
import com.company.tipod.dto.UserInfoDTO;
import com.company.tipod.jwt.JwtTokenUtils;
import com.company.tipod.model.SystemUser;
import com.company.tipod.service.UserService;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/public/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class AuthController {
	final AuthenticationManager authenticationManager;
	final JwtTokenUtils jwtTokenUtils;
	final UserService userService;
	final PasswordEncoder passwordEncoder;

	// ============================================================================================
	@PostMapping(value = "/forgot-passowrd/step-1")
	ResponseEntity<String> forgotPassowrdStep1(
			@RequestBody @Valid Step1DTO step1DTO
	) {
		try {
			SystemUser systemUser = userService.findByEmail(step1DTO.getEmail()).get();
			String jwtToken = jwtTokenUtils.createToken(systemUser.getUsername(), Arrays.asList(systemUser.getRole()));
			return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body(jwtToken);
		} catch (NoSuchElementException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}



	// ============================================================================================
	@PostMapping(value = "/forgot-passowrd/step-2")
	ResponseEntity<String> forgotPassowrdStep2(
			@RequestBody @Valid Step2DTO step2DTO
	) {
		try {
			if (step2DTO.getToken() == null || step2DTO.getNewPassowrd() == null)
				throw new IllegalArgumentException();

			String username = jwtTokenUtils.getUsername(step2DTO.getToken());
			SystemUser systemUser = userService.findByEmail(username).orElse(null);

			//PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			String pwd = passwordEncoder.encode(step2DTO.getNewPassowrd());
			systemUser.setPassword(pwd);

			userService.save(systemUser);

			return ResponseEntity.ok().body("New password set...");
		} catch (NoSuchElementException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch (IllegalArgumentException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}



	// ============================================================================================
	@RequestMapping(value = "/sign-in", method = {RequestMethod.POST//, RequestMethod.OPTIONS
	})
	ResponseEntity<String> login(
//			 String username
//			, String password
			@RequestBody @Valid LoginDTO loginDTO
			//, @Autowired AuthenticationManager authenticationManager // ??????????????????
			, HttpServletResponse httpServletResponse
	) {
		//LoginDTO loginDTO = new LoginDTO(username, password);
		log.info("login: " + loginDTO.getUsername() + " " + loginDTO.getPassword());

		UsernamePasswordAuthenticationToken token =
				new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
		Authentication authentication = authenticationManager.authenticate(token);
		log.info("authenticated: " + loginDTO.getUsername() + " " + loginDTO.getPassword());

		SystemUser systemUser = (SystemUser)authentication.getPrincipal(); //userService.findByEmail(loginDTO.getUsername()).get();

		// ---Auth passed---
		String jwtToken = jwtTokenUtils.createToken(loginDTO.getUsername(), Arrays.asList(systemUser.getRole()));

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
			@RequestParam @Valid UUID id
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
			Exception ex
	) {
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

