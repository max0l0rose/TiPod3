package com.osdb.octipod;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DocConf {
	@Bean
	public OpenAPI customOpenApi(@Value("${application-description}")String appDescription,
	                             @Value("${application-version}")String appVersion
	) {
		return new OpenAPI().info(new Info().title("OctiPod API---")
						.version(appVersion)
						.description(appDescription)
						.license(new License().name("Apache 2.0---")
								.url("http://springdoc.org---"))
						.contact(new Contact().name("username---")
								.email("test@gmail.com")))
				.servers(Arrays.asList(
								new Server().url("http://localhost:8080").description("Dev service---")
						)
				);
	}
}
