package com.osdb.octipod.jwt;

import com.osdb.octipod.model.SystemUser;
import java.util.ArrayList;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUserDetails create(SystemUser user) {
        return new JwtUserDetails(
                null, //user.getId(),
                user.getFirstName(),
                user.getPassword(),
                new ArrayList<>(
                        //user.getRole()
                ),
                true
//                mapToGrantedAuthorities(new ArrayList<>(user.getRoles())),
//                user.getStatus() == null || user.getStatus() == Status.ACTIVE
                //user.getUpdated()
        );
    }


//    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
//        return userRoles.stream()
//                .map(role ->
//                        new SimpleGrantedAuthority(role.getName())
//                ).collect(Collectors.toList());
//    }
}
