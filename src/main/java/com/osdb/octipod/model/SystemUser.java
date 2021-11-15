package com.osdb.octipod.model;

import javax.persistence.*;

@Entity
@Table(name = "system_user", schema = "public", catalog = "postgres")
public class SystemUser {
	private Object id;
	private String firstName;
	private String lastName;
	private String email;
	private Object role;
	private String password;

	@Id
	@Column(name = "id", nullable = false)
	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	@Basic
	@Column(name = "first_name", nullable = true, length = 128)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Basic
	@Column(name = "last_name", nullable = true, length = 128)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Basic
	@Column(name = "email", nullable = true, length = 320)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Basic
	@Column(name = "role", nullable = true)
	public Object getRole() {
		return role;
	}

	public void setRole(Object role) {
		this.role = role;
	}

	@Basic
	@Column(name = "password", nullable = true, length = -1)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SystemUser that = (SystemUser) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
		if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
		if (email != null ? !email.equals(that.email) : that.email != null) return false;
		if (role != null ? !role.equals(that.role) : that.role != null) return false;
		if (password != null ? !password.equals(that.password) : that.password != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (role != null ? role.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		return result;
	}
}
