package com.osdb.octipod.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class LoginDTO {
	@NotNull
	@Size(min = 3)
	String username;
	@NotNull
	@Size(min = 4)
	String password;
}
