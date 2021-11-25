package com.osdb.octipod.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginDTO implements Username, Password {
	String username;
	String password;

	@Override
	public String toString() {
		return "LoginDTO{" +
				       "username='" + username + '\'' +
//				       ", password='" + password + '\'' +
				       '}';
	}
}
