package com.osdb.octipod.jwt;

import demospringmvc.Services.UserService;
import demospringmvc.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of {@link UserDetailsService} interface for {@link JwtUser}.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> op = userService.findByName(name);

        if (!op.isPresent()) {
            throw new UsernameNotFoundException("loadUserByUsername: User with username: "
                    + name + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(op.get());
        log.info("IN loadUserByUsername - user with username: {} successfully loaded",
                        name);
        return jwtUser;
    }
}
