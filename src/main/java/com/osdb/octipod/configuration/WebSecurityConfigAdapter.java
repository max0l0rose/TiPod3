package com.osdb.octipod.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osdb.octipod.controller.AuthenticationCustomEntryPoint;
import com.osdb.octipod.jwt2.JwtTokenFilter2;
import com.osdb.octipod.model.HelloObject;
import com.osdb.octipod.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.ChangeSessionIdAuthenticationStrategy;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfAuthenticationStrategy;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;

//@Configuration
//@EnableGlobalMethodSecurity(
//        prePostEnabled = true
//        //securedEnabled = true,
//        //jsr250Enabled = true
//)
//class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
//}


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

	//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//				.withUser("user1").password(passwordEncoder().encode("pwd"))
//				.authorities("ROLE_USER");
//	}


	//private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";
	//private static final String LOGIN_ENDPOINT = "/api/v1/auth/login";

	final UserRepository userRepository;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//				.withUser("user1").password(
//						//passwordEncoder().encode(
//						 "pwd"
//						// )
//				)
//				.authorities("ROLE_USER");
		auth.userDetailsService(email -> userRepository
				.findByEmail(email)
				.orElseThrow(
						() -> new UsernameNotFoundException(
								String.format("User: %s, not found", email)
						)
				));
	}


	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

	//@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return (request, response, ex) -> {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			//response.setContentType(MediaType.APPLICATION_JSON_VALUE);

			ServletOutputStream out = response.getOutputStream();
			PrintWriter printWriter = new PrintWriter(out);
			//new ObjectMapper().writeValue(out, new HelloObject(1L, "HttpServletResponse.SC_FORBIDDEN...."));
			//new ObjectMapper().writeValue(out, "HttpServletResponse.SC_FORBIDDEN....");
			printWriter.write("HttpServletResponse.SC_FORBIDDEN....");
			printWriter.flush();
			out.flush();
		};
	}

	@Bean
	public AuthenticationFailureHandler failureHandler() {
		return (request, response, ex) -> {
			throw ex;
		};
	}

	final JwtTokenFilter2 jwtTokenFilter;


//	@Bean
//	public SessionAuthenticationStrategy sessionAuthenticationStrategy() {
//		return new CompositeSessionAuthenticationStrategy(Arrays.asList(
//				new ChangeSessionIdAuthenticationStrategy(),
//				new CsrfAuthenticationStrategy(csrfTokenRepository())
//		));
//	}


	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf()
				//.disable()
					.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.and()
					.sessionManagement()
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
					.authorizeRequests()
	//				//.antMatchers("/*").permitAll()
	//				.antMatchers("/api/v1/public/auth/hello-world").authenticated()
					.antMatchers("/**/hello-world").authenticated()
					.anyRequest().permitAll()
				.and()
					.exceptionHandling().accessDeniedHandler(accessDeniedHandler())
				.and()
					.exceptionHandling().authenticationEntryPoint(new AuthenticationCustomEntryPoint())
	//				//.formLogin().disable()
					//.httpBasic()


					//.anyRequest()
					//	.authenticated()

				.and()
	//					.oauth2ResourceServer()
	//						.jwt();

						.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
						//.addFilterAfter(jwtTokenFilter, CsrfFilter.class)
//		.apply(new JwtTokenFilterConfigurer())
				;
////		JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(//jwtTokenProvider
////		);

//		//http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
	}


//	@Bean
//	@Override
//	public UserDetailsService userDetailsService() {
//		UserDetails user1 =
//				User.withDefaultPasswordEncoder()
//						.username("user")
//						.password("pwd")
//						.roles("USER")
//						.build();
//		UserDetails user2 =
//				User.withDefaultPasswordEncoder()
//						.username("user2")
//						.password("pwd")
//						.roles("USER")
//						.build();
//
//		return new InMemoryUserDetailsManager(user1, user2);
//	}

}


////@EnableWebMvc
//public class WebConfig implements WebMvcConfigurer {
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/api/**")
//				.allowedOrigins("*")
//				.allowedMethods("*")//, "POST", "GET","PUT", "DELETE", "OPTIONS"
////				.allowedHeaders("Access-Control-Allow-Methods",
////							"POST, GET, OPTIONS, PUT, DELETE",
////							"Access-Control-Allow-Headers",
////							"Origin, X-Requested-With, Content-Type, Accept"
////				)
//		;
////					.exposedHeaders("header1", "header2")
//		//.allowCredentials(true)
//		//.maxAge(3600);
//
////		registry.addMapping("/**")
////				.allowedHeaders("Access-Control-Allow-Origin",
////						"*",
////						"Access-Control-Allow-Methods",
////						"POST, GET, OPTIONS, PUT, DELETE",
////						"Access-Control-Allow-Headers",
////						"Origin, X-Requested-With, Content-Type, Accept")
////				.allowedOrigins("*")
////				.allowedMethods("*");
//
//	}
//}
