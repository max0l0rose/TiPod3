package com.osdb.octipod.security;

import lombok.Data;

import java.util.Collection;
import java.util.List;
//
//@Data
//public class UserDetailsImpl implements UserDetails {
//
//    private final String username;
//    private final String password;
//    private final List<SimpleGrantedAuthority> authorities;
//    private final boolean isActive;
//
//    public UserDetailsImpl(String username, String password, List<SimpleGrantedAuthority> authorities, boolean isActive) {
//        this.username = username;
//        this.password = password;
//        this.authorities = authorities;
//        this.isActive = isActive;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return isActive;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return isActive;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return isActive;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return isActive;
//    }
//
//    public static UserDetails fromUser(User user) {
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(), user.getPassword(),
//                user.getStatus().equals(Status.ACTIVE),
//                user.getStatus().equals(Status.ACTIVE),
//                user.getStatus().equals(Status.ACTIVE),
//                user.getStatus().equals(Status.ACTIVE),
//                user.getRole().getAuthorities()
//        );
//    }
//}
