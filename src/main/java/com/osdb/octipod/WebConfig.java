package com.osdb.octipod;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
				.allowedOrigins("http://domain2.com")
				.allowedMethods("OPTIONS")//, "POST", "GET","PUT", "DELETE", "OPTIONS"
		;

	}
}
