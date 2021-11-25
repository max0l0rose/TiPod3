package com.osdb.octipod.dto;

import com.osdb.octipod.model.SystemUser;
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
