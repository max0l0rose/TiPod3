package com.osdb.octipod.jwt;
//
//import com.osdb.octipod.model.SystemUser;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public final class JwtUserFactory {
//
//    public JwtUserFactory() {
//    }
//
//    public static JwtUser create(SystemUser user) {
//        return new JwtUser(
//                null, //user.getId(),
//                user.getFirstName(),
//                user.getPassword(),
//                new ArrayList<>(
//                        //user.getRole()
//                ),
//                true
////                mapToGrantedAuthorities(new ArrayList<>(user.getRoles())),
////                user.getStatus() == null || user.getStatus() == Status.ACTIVE
//                //user.getUpdated()
//        );
//    }
//
//
////    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
////        return userRoles.stream()
////                .map(role ->
////                        new SimpleGrantedAuthority(role.getName())
////                ).collect(Collectors.toList());
////    }
//}
