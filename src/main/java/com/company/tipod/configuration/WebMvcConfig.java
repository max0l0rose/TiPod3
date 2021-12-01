package com.company.tipod.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Value("${app.url}")
	String appURL;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
				.addMapping("/api/**")
				.allowedOrigins(appURL)
				.allowedMethods("OPTIONS"); //, "POST", "GET", "PUT", "DELETE", "OPTIONS"
	}
}
