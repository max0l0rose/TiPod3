package com.osdb.octipod.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.authority.AuthorityUtils;

@Entity
@Table(name = "system_user")
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
public class SystemUser implements UserDetails
{
	@Id
	@Column(name = "id", nullable = false)
	UUID id;

	@Column(name = "first_name", length = 128)
	String firstName;

	@Column(name = "last_name", length = 128)
	String lastName;

	@Column(name = "email", length = 320)
	String email;

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	RoleEnum role;

	// @Lob ??
	@Column(name = "password")
	@JsonIgnore
	String password;



	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList(role.name());
			// AuthorityUtils.authorityListToSet(AuthorityUtils.NO_AUTHORITIES);
	}

	@Override
	@JsonIgnore
	public String getUsername() {
		return email;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}
}