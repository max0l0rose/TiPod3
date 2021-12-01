package com.company.tipod.jwt;

//import org.hibernate.mapping.Array;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public final class JwtUserFactory {
//
////    public JwtUserFactory() {
////    }
//
//    public static JwtUserDetails create(SystemUser user) {
//        List<GrantedAuthority> grantedAuthorities = mapToGrantedAuthorities(Arrays.asList(user.getRole()));
//        return new JwtUserDetails(
//                null, //user.getId(),
//                user.getFirstName(),
//                user.getPassword(),
//                grantedAuthorities,
//                true
//        );
//    }
//
//
//    private static List<GrantedAuthority> mapToGrantedAuthorities(List<RoleEnum> userRoles) {
//        return userRoles.stream()
//                .map(role ->
//                        new SimpleGrantedAuthority(role.name())
//                ).collect(Collectors.toList());
//    }
//}
