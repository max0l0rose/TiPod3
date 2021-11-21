package com.osdb.octipod.jwt;

//import com.osdb.octipod.model.SystemUser;
//import com.osdb.octipod.service.UserService;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;

//@Service
//@Slf4j
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class JwtUserDetailsService implements UserDetailsService {
//    final UserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<SystemUser> op = userService.findByEmail(email);
//
//        if (!op.isPresent()) {
//            throw new UsernameNotFoundException("loadUserByUsername: User with email: "
//                    + email + " not found");
//        }
//
//        //JwtUserDetails jwtUser = JwtUserFactory.create(op.get());
//        log.info("IN loadUserByUsername - user with email: {} successfully loaded", email);
//
//        SystemUser user = op.get();
//		return user;
//    }
//}
