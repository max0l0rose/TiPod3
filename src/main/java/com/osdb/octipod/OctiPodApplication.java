package com.osdb.octipod;

import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.HEADER;

@SpringBootApplication
public class OctiPodApplication {


//	@Bean
//	public OperationCustomizer customize() {
//		return (operation, handlerMethod) -> {
//			boolean isNotGet = !handlerMethod.getMethod().getName().contains("get");
//
//			if(isNotGet) {
//				return operation.addParametersItem(
//						new Parameter()
//								.in(HEADER.toString())
//								.required(false)
//								.name("X-XSRF-TOKEN"));
//			}
//
//			return null;
//		};
//	}


	public static void main(String[] args) {
		SpringApplication.run(OctiPodApplication.class, args);
	}

}
