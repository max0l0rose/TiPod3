package com.osdb.octipod;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class OctiPodApplication {


//	@Bean
//	public GroupedOpenApi publicUserApi() {
//		return GroupedOpenApi.builder()
//				.group("Users")
//				.pathsToMatch("/users/**")
//				.build();
//	}

	@Bean
	public OpenAPI customOpenApi(@Value("${application-description}")String appDescription,
	                             @Value("${application-version}")String appVersion
	                             //,@Value("${springdoc.version}") String av
	) {
		return new OpenAPI().info(new Info().title("Application API-------")
						.version(appVersion)
						.description(appDescription)
						.license(new License().name("Apache 2.0----------")
								.url("http://springdoc.org---------"))
						.contact(new Contact().name("username-------")
								.email("test@gmail.com--------")))
				.servers(Arrays.asList(
						//List.of(
							new Server().url("http://localhost:8080").description("Dev service-------"),
							new Server().url("http://localhost:8082").description("Beta service-----------")
						)
				);
	}


	public static void main(String[] args) {
		SpringApplication.run(OctiPodApplication.class, args);
	}

}
