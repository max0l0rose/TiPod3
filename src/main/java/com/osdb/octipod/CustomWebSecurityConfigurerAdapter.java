package com.osdb.octipod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//				.withUser("user1").password(passwordEncoder().encode("pwd"))
//				.authorities("ROLE_USER");
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/").permitAll()
				.anyRequest().authenticated()
				.and()
				.httpBasic()
				//.authenticationEntryPoint(authenticationEntryPoint)
		;

		//http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user1 =
				User.withDefaultPasswordEncoder()
						.username("user")
						.password("pwd")
						.roles("USER")
						.build();
		UserDetails user2 =
				User.withDefaultPasswordEncoder()
						.username("user2")
						.password("pwd")
						.roles("USER")
						.build();

		return new InMemoryUserDetailsManager(user1, user2);
	}

}
