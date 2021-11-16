package com.osdb.octipod.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "system_user")
@Data
public class SystemUser {
	@Id
	@Column(name = "id", nullable = false)
	private UUID id;

	@Column(name = "first_name", length = 128)
	private String firstName;

	@Column(name = "last_name", length = 128)
	private String lastName;

	@Column(name = "email", length = 320)
	private String email;

	@Column(name = "role")
	private RoleEnum role;

	@Lob
	@Column(name = "password")
	private String password;
}