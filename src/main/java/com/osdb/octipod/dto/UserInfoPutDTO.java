package com.osdb.octipod.dto;

import com.osdb.octipod.model.SystemUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoPutDTO {
	String firstName;
	String lastName;
	String role;

//	public static UserInfoPutDTO fromSystemUser(SystemUser u) {
//		return new UserInfoPutDTO(u.getFirstName(), u.getLastName(), u.getRole().name());
//	}
}
