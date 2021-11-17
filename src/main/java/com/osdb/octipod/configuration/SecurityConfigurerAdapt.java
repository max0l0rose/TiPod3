package com.osdb.octipod.configuration;
//
//import com.osdb.octipod.jwt.JwtTokenFilter;
//import com.osdb.octipod.jwt.JwtTokenProvider;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.DefaultSecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
////@Configuration
//public class SecurityConfigurerAdapt extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
//    private JwtTokenProvider jwtTokenProvider;
//
//    public SecurityConfigurerAdapt(JwtTokenProvider jwtTokenProvider) {
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    @Override
//    public void configure(HttpSecurity httpSecurity) throws Exception {
//        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(jwtTokenProvider);
//        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//}
