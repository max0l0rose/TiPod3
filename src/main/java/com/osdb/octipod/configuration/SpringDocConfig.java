package com.osdb.octipod.configuration;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.servers.Server;
import lombok.experimental.FieldDefaults;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

import static lombok.AccessLevel.PRIVATE;

@Configuration
@FieldDefaults(level = PRIVATE)
public class SpringDocConfig {

	@Value("${app.title}")
	String appTitle;

	@Value("${app.description}")
	String appDesc;

	@Value("${app.version}")
	String appVersion;

	@Value("${app.url}")
	String appURL;

	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI().info(
				new Info()
						.title(appTitle)
						.version(appVersion)
						.description(appDesc)
						.license(
								new License()
										.name("Apache 2.0")
										.url("http://springdoc.org")
						)
						.contact(
								new Contact()
										.name("admin")
										.email("admin@octipod.com")
						))
				.servers(Collections.singletonList(new Server().url(appURL)));
	}

	@Bean
	public OperationCustomizer customize() {
		return (operation, handlerMethod) -> {
			boolean isNotGet = !handlerMethod.getMethod().getName().contains("get");

			if(isNotGet) {
				return operation.addParametersItem(
						new Parameter()
								.in(ParameterIn.HEADER.toString())
								.required(false)
								.name("X-XSRF-TOKEN"));
			}

			return null;
		};
	}
}
