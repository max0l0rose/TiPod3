package com.osdb.octipod;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
				.allowedOrigins("http://domain2.com")
				.allowedMethods("POST", "GET","PUT", "DELETE", "OPTIONS")//, "POST", "GET","PUT", "DELETE", "OPTIONS"
//				.allowedHeaders(
//						"Access-Control-Allow-Methods",
//						"Access-Control-Allow-Headers",
//						"Access-Control-Allow-Origin",
//						"Origin, X-Requested-With, Content-Type, Accept"
//						,
//						"POST, GET, OPTIONS, PUT, DELETE"
//				)
		;
//					.exposedHeaders("header1", "header2")
				//.allowCredentials(true)
				//.maxAge(3600);

//		registry.addMapping("/**")
//				.allowedHeaders("Access-Control-Allow-Origin",
//						"*",
//						"Access-Control-Allow-Methods",
//						"POST, GET, OPTIONS, PUT, DELETE",
//						"Access-Control-Allow-Headers",
//						"Origin, X-Requested-With, Content-Type, Accept")
//				.allowedOrigins("http://domain2.com")
//				.allowedMethods("*");
	}
}
