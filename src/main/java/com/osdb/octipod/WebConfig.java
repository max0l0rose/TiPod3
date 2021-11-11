package com.osdb.octipod;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
				.allowedOrigins("http://domain2.com:8080")
					.allowedMethods("POST", "OPTIONS")
					.allowedHeaders("content-type", "Access-Control-Request-Method", "Access-Control-Request-Headers", "Origin")
//					.exposedHeaders("header1", "header2")
				.allowCredentials(false).maxAge(3600);
	}
}
