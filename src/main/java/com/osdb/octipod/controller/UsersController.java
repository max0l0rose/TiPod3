package com.osdb.octipod.controller;

import com.osdb.octipod.model.HelloObject;
import com.osdb.octipod.model.RoleEnum;
import com.osdb.octipod.model.SystemUser;
import com.osdb.octipod.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/private/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class UsersController {

	final UserService userService;


	// ============================================================================================
	@PatchMapping(value = "/{id}")
	ResponseEntity<SystemUser> userPatch(
			@PathVariable UUID id,
			@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName,
			@RequestParam(required = false) String role,
			@RequestParam Map<String, String> reqParam
	) {
		try {
			if (firstName == null && lastName == null && role == null)
				throw new IllegalArgumentException();

			SystemUser systemUser = userService.findById(id).get();
			int pc = 0;
			if (firstName!=null) {
				systemUser.setFirstName(firstName);
				pc++;
			}
			if (lastName!=null) {
				systemUser.setLastName(lastName);
				pc++;
			}

			if (role!=null) {
				try {
					RoleEnum roleEnum = RoleEnum.valueOf(role);
					systemUser.setRole(roleEnum);
					pc++;
				} catch (IllegalArgumentException | NullPointerException e) {
					//e.printStackTrace();
				}
			}

			if (pc != reqParam.size())
				throw new IllegalArgumentException();

			userService.save(systemUser);
			return ResponseEntity.ok(systemUser);
		} catch (IllegalArgumentException | PropertyReferenceException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SystemUser());
		}
		catch (NoSuchElementException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SystemUser());
		}
	}


	// ============================================================================================
	@PutMapping(value = "/{id}")
	ResponseEntity<SystemUser> userPut(
			@PathVariable UUID id,
			@RequestParam String firstName,
			@RequestParam(defaultValue = "") String lastName,
			@RequestParam String role
	) {
		try {
			SystemUser systemUser = userService.findById(id).get();
			systemUser.setFirstName(firstName);
			systemUser.setLastName(lastName);
			RoleEnum roleEnum = RoleEnum.valueOf(role);
			systemUser.setRole(roleEnum);
			userService.save(systemUser);
			return ResponseEntity.ok(systemUser);
		} catch (IllegalArgumentException | PropertyReferenceException ex) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new SystemUser());
		}
		catch (NoSuchElementException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SystemUser());
		}
	}



	// ============================================================================================
	@GetMapping()
	ResponseEntity<List<SystemUser>> users(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id,asc") String sort
	) {
		try {
			int c = sort.indexOf(',');
			String field = sort.substring(0, c).trim();
			String dir = sort.substring(c + 1).trim();

			Pageable pageable =	PageRequest.of(page, size,
					"desc".equals(dir) ? Sort.by(field).descending() : Sort.by(field).ascending());
			Page<SystemUser> pageSystemUser = userService.getPage(pageable);

			return ResponseEntity.ok(pageSystemUser.getContent());
		}
		catch (IllegalArgumentException | NoSuchElementException | PropertyReferenceException | StringIndexOutOfBoundsException ex) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Collections.emptyList());
		}
	}



	// ============================================================================================
	@GetMapping(value = "/{id}")
	ResponseEntity<SystemUser> userInfo(
			@PathVariable UUID id
	) {
		try {
			SystemUser systemUser = userService.findById(id).get();
			return ResponseEntity.ok(systemUser);
		} catch (NoSuchElementException ex) {
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SystemUser());
	}






	// ============================================================================================
	@RequestMapping(value = "/hello-world", method = {RequestMethod.PUT, RequestMethod.GET//, RequestMethod.OPTIONS
	})
	@CrossOrigin(origins = {"http://domain21.com"}//, methods = {RequestMethod.DELETE, RequestMethod.POST}
	)
	ResponseEntity<HelloObject> hello() {
		HelloObject helloObject = new HelloObject(1L, "Hello world...");
		return ResponseEntity.ok(helloObject);
	}

}


