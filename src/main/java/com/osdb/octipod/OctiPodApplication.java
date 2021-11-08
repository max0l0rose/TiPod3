package com.osdb.octipod;

import com.osdb.octipod.model.HelloObject;
import com.osdb.octipod.repo.HelloObjectRepo;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;

@SpringBootApplication(
//		exclude={DataSourceAutoConfiguration.class}
)
public class OctiPodApplication {


	@Configuration
	//@EnableWebMvc
	public class WebConfig implements WebMvcConfigurer {

//		$(document).ready(function() {
//			$.ajax({
//					url: "http://localhost:8080/greeting"
//    }).then(function(data, status, jqxhr) {
//				$('.greeting-id').append(data.id);
//				$('.greeting-content').append(data.content);
//				console.log(jqxhr);
//			});
//		});


//		@Bean
//		public CorsConfigurationSource corsConfigurationSource() {
//			CorsConfiguration configuration = new CorsConfiguration();
//			configuration.setAllowedOrigins(Arrays.asList("*"));
//			configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//			configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
//			configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
//			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//			source.registerCorsConfiguration("/**", configuration);
//			return source;
//		}

		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/api/**")
					.allowedOrigins("http://domain2.com")
//					.allowedMethods("POST", "OPTIONS")
//					.allowedHeaders("content-type", "Access-Control-Request-Method", "Access-Control-Request-Headers", "Origin")
//					.exposedHeaders("header1", "header2")
					.allowCredentials(false).maxAge(3600);
		}
	}


//	@Bean
//	public GroupedOpenApi publicUserApi() {
//		return GroupedOpenApi.builder()
//				.group("Users")
//				.pathsToMatch("/users/**")
//				.build();
//	}


	@Value("${spring.profiles.active}")
	String pActive;

	@Bean
	//@Profile("!dev")
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


//	@Bean
//	//@Qualifier(value = "entityManager")
//	public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
//		return entityManagerFactory.createEntityManager();
//	}

	//@PersistenceContext
	//@Autowired
	//EntityManager entityManager;

//	@Bean
//	boolean InitDB(HelloObjectRepo helloObjectRepo, EntityManager entityManager) {
//		helloObjectRepo.save(new HelloObject(1L, "Qqqq"));
//		return true;
//	}


	public static void main(String[] args) {
		SpringApplication.run(OctiPodApplication.class, args);
	}

}
