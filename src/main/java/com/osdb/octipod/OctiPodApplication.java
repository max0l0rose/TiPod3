package com.osdb.octipod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OctiPodApplication {


//	@Bean
//	public GroupedOpenApi publicUserApi() {
//		return GroupedOpenApi.builder()
//				.group("Users")
//				.pathsToMatch("/users/**")
//				.build();
//	}
//
//	@Bean
//	public OpenAPI customOpenApi(@Value("${application-description}")String appDescription,
//	                             @Value("${application-version}")String appVersion) {
//		return new OpenAPI().info(new Info().title("Application API")
//						.version(appVersion)
//						.description(appDescription)
//						.license(new License().name("Apache 2.0")
//								.url("http://springdoc.org"))
//						.contact(new Contact().name("username")
//								.email("test@gmail.com")))
//				.servers(List.of(new Server().url("http://localhost:8080")
//								.description("Dev service"),
//						new Server().url("http://localhost:8082")
//								.description("Beta service")));
//	}


	public static void main(String[] args) {
		SpringApplication.run(OctiPodApplication.class, args);
	}

}
