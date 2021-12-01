package com.company.tipod.jwt;

//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//
//@Getter
//@AllArgsConstructor
//public class JwtUserDetails extends SystemUser implements UserDetails {
//
////	private final Collection<? extends GrantedAuthority> authorities;
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return AuthorityUtils.createAuthorityList(role.name());
//	}
//
//	@Override
//	public String getUsername() {
//		return email;
//	}
//
//
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}
//}
