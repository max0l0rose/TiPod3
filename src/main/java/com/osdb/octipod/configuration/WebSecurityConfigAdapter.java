package com.osdb.octipod.configuration;

import com.osdb.octipod.jwt2.JwtTokenFilter2;
import com.osdb.octipod.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

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


	//@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return (request, response, ex) -> {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			//response.setContentType(MediaType.APPLICATION_JSON_VALUE);

			ServletOutputStream out = response.getOutputStream();
			PrintWriter printWriter = new PrintWriter(out);
			//new ObjectMapper().writeValue(out, new HelloObject(1L, "HttpServletResponse.SC_FORBIDDEN...."));
			//new ObjectMapper().writeValue(out, "HttpServletResponse.SC_FORBIDDEN....");
			printWriter.write("WebSecurityConfigAdapter: AccessDeniedHandler: HttpServletResponse.SC_FORBIDDEN....");
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

//	private static final ClearSiteDataHeaderWriter.Directive[] SOURCE =
//			{CACHE, COOKIES, STORAGE, EXECUTION_CONTEXTS};


	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf()
					.disable()
					//.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				//.and()
					.sessionManagement()
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
					.authorizeRequests()
	//				//.antMatchers("/*").permitAll()
	//				.antMatchers("/api/v1/public/auth/hello-world").authenticated()
					.antMatchers("/api/v1/private/**").hasAnyAuthority("ADMIN", "ADMIN_READONLY")
					.anyRequest().permitAll()
				.and()
					.exceptionHandling().accessDeniedHandler(accessDeniedHandler())

//				.and()
//					.logout(logout -> logout
//										.logoutUrl("/logout")
//										.addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(SOURCE)))
//					)

//					.logout() // This is missing and is important
//						.logoutUrl("/logout")
						//.logoutSuccessUrl("/api/v1/public/auth/sign-in1")

//					.exceptionHandling().authenticationEntryPoint(new AuthenticationCustomEntryPoint())
//	//				//.formLogin().disable()
//				.and()
//				.httpBasic().disable()

					//.anyRequest()
					//	.authenticated()

//				.and()
	//					.oauth2ResourceServer()
	//						.jwt();

				.and()
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
