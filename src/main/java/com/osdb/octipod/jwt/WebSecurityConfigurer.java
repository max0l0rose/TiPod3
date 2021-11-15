package com.osdb.octipod.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security configuration class for JWT based Spring Security application.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */


//@Configuration
//@EnableGlobalMethodSecurity(
//        prePostEnabled = true
//        //securedEnabled = true,
//        //jsr250Enabled = true
//)
//class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
//}


@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    //private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";
    //private static final String LOGIN_ENDPOINT = "/api/v1/auth/login";


    @Autowired
    public WebSecurityConfigurer(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                //.and()
                .authorizeRequests()

                    .antMatchers("/users/**").authenticated()//.hasRole("ADMIN")
                    .antMatchers("/faculties/**").hasRole("ADMIN")
//                    .antMatchers("/**").permitAll()
//                    .anyRequest()
//                        .authenticated()

                .and()
                .apply(new JwtConfigurer(jwtTokenProvider))

                .and()
                .formLogin()
                    .loginPage("/login")
                        .permitAll();
    }
}
