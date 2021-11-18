package com.osdb.octipod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.core.authority.AuthorityUtils;

@SpringBootApplication
public class OctiPodApplication {

//	@Configuration
//	class SpringDataRestCustomization //extends RepositoryRestConfigurerAdapter
//	{
//
////	@Bean
////	CorsConfigurationSource corsConfigurationSource() {
////		CorsConfiguration configuration = new CorsConfiguration();
////		configuration.setAllowedOrigins(Arrays.asList("https://example.com"));
////		configuration.setAllowedMethods(Arrays.asList("GET","POST"));
////		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////		source.registerCorsConfiguration("/**", configuration);
////		return source;
////	}
//
////	@Override
////	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
////		config.getCorsRegistry().addMapping("/**")
////				.allowedOrigins("http://localhost:9000");
////	}
//	}


	public static void main(String[] args) {
		SpringApplication.run(OctiPodApplication.class, args);
	}
}
