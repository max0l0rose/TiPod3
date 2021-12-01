package com.company.tipod.dto;

import com.company.tipod.model.SystemUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
	String firstName;
	String lastName;
	String email;
	String role;

	public static UserInfoDTO fromSystemUser(SystemUser u) {
		return new UserInfoDTO(u.getFirstName(), u.getLastName(), u.getEmail(), u.getRole().name());
	}
}
