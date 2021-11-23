package com.osdb.octipod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.core.authority.AuthorityUtils;

@SpringBootApplication(
		//exclude = {ErrorMvcAutoConfiguration.class}
)
public class OctiPodApplication {

//	@Configuration
//	class SpringDataRestCustomization //extends RepositoryRestConfigurerAdapter
//	{
//
////	@Bean
////	CorsConfigurationSource corsConfigurationSource() {
////		CorsConfiguration configuration = new CorsConfiguration();
////		configuration.setAllowedOrigins(Arrays.asList("https://example.com"));
////		configuration.setAllowedMethods(Arrays.asList("GET","POST"));
////		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////		source.registerCorsConfiguration("/**", configuration);
////		return source;
////	}

//	// Used by spring security if CORS is enabled.
//	@Bean
//	public CorsFilter corsFilter() {
//		UrlBasedCorsConfigurationSource source =
//				new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true);
//		config.addAllowedOrigin("*");
//		config.addAllowedHeader("*");
//		config.addAllowedMethod("*");
//		source.registerCorsConfiguration("/**", config);
//		return new CorsFilter(source);
//	}

////	@Override
////	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
////		config.getCorsRegistry().addMapping("/**")
////				.allowedOrigins("http://localhost:9000");
////	}
//	}


	public static void main(String[] args) {
		SpringApplication.run(OctiPodApplication.class, args);
	}
}


//	NEED TO DO
//
//			        Implement API for getting auth user details
//			        REQUEST EXAMPLE
//
//			        GET /api/v1/private/auth/user
//
//					                           RESPONSE EXAMPLE
//
//					                           200 – OK
//
//					                           {
//						                           "firstName": "string",
//						                           "lastName": "string",
//						                           "email": "string",
//						                           "role": "string"
//					                           }



//	Configure CI/CD for Octipod BE



//
//	NEED TO DO
//
//			        Add API for Sign Out: /api/v1/private/auth/sign-out
//					                                             Remove AUTH_TOKEN and invalidate the session (JWT token)



//	NEED TO DO
//
//			        Add API for getting all the users from DB
//			        Sorting and pagination should be supported
//			        Only users with ADMIN and ADMIN_READONLY roles should have an access
//			        REQUEST EXAMPLE
//
//			        GET /api/v1/private/users?page=0&size=10&sort=id,asc
//
//					                           RESPONSE EXAMPLE
//
//					                           200 – OK
//					                           403 – If the user doesn't have an access to perform the operation
//
//					                           {
//						                           "result": [{
//							                           "id": "string",
//							                           "firstName": "string",
//							                           "lastName": "string",
//							                           "email": "string",
//							                           "role": "ROLE_ADMIN"
//						                           }],
//						                           "total": 0,
//						                           "page": 0,
//						                           "size": 0
//					                           }



//	NEED TO DO
//
//			        Add API for getting user by ID
//			        Only users with ADMIN and ADMIN_READONLY roles should have an access
//			        REQUEST EXAMPLE
//
//			        GET /api/v1/private/users/{id}
//
//					                           RESPONSE EXAMPLE
//
//					                           200 – OK
//					                           403 – If the user doesn't have an access to perform the operation
//
//					                           {
//						                           "id": "string",
//						                           "firstName": "string",
//						                           "lastName": "string",
//						                           "email": "string",
//						                           "role": "ROLE_ADMIN"
//					                           }



//	NEED TO DO
//
//			        Add PUT API for editing user details
//			        Add PATCH API for editing user details
//			        Only users with ADMIN role should have an access
//			        REQUEST EXAMPLE
//
//			        PUT /api/v1/private/users/{id}
//
//					                           {
//						                           "firstName": "string",
//						                           "lastName": "string",
//						                           "role": "string"
//					                           }
//
//					                           or
//
//					                           PATCH /api/v1/private/users/{id}
//
//							                                                {
//							                                                    "firstName": "string"
//							                                                }
//
//																			RESPONSE EXAMPLE
//
//							                                                200 – OK
//							                                                400 – If the required field is missing
//							                                                403 – If the user doesn't have an access to perform the operation
//
//							                                                {
//								                                                "id": "string",
//								                                                "firstName": "string",
//								                                                "lastName": "string",
//								                                                "role": "string"
//							                                                }



//	NEED TO DO
//
//			        Add /api/v1/public/auth/forgot-passowrd/step-1 API
//					                          Add /api/v1/public/auth/forgot-passowrd/step-2 API
//
//FORGOT PASSWORD STEP 1
//
//							                                            REQUEST:
//
//							                                            POST /api/v1/public/auth/forgot-passowrd/step-1
//									                                                               {
//									                                                                    "email": "string"
//									                                                               }
//
//									                                                               RESPONSE:
//
//									                                                               204 – No Content
//									                                                               400 – If the email address is invalid
//									                                                               FORGOT PASSWORD STEP 2
//
//									                                                               REQUEST:
//
//									                                                               POST /api/v1/public/auth/forgot-passowrd/step-2
//											                                                                                  {
//											                                                                                  "newPassowrd": "string",
//											                                                                                  "token": "string"
//											                                                                                  }
//
//											                                                                                  RESPONSE:
//
//											                                                                                  204 – No Content
//											                                                                                  400 – If a token or email address is invalid
//											                                                                                  EMAIL TEXT FORMAT
//
//											                                                                                  username – firstName lastName
//
//<html>
//<body>
//<p>Dear ${username},</p>
//
//<p>You've requested a password reset.</p>
//<p>Please follow the <a href="${resetUrl}?token=${fpToken}">link</a> to confirm it.</p>
//
//<p>If you did not initiate this request, please contact us immediately at support@octipod.io</p>
//
//<p>Kind regards,</p>
//<p>OctiPod Team</p>
//</body>
//</html>


//	NEED TO DO
//
//			        Add sendgrid-java dependency
//			        Add org.apache.velocity dependency
//			        Configure VelocityEngine bean
//			        CONFIGS:




//	NEED TO DO
//
//			        Configure GlobalExceptionHandler (@ControllerAdvice)
//					                                                           Add custom BusinessException extends RuntimeException
//					                                                           Add custom NotFoundException extends BusinessException
//					                                                           Add custom AlreadyExistException extends BusinessException
//					                                                           Handle all BusinessException as bad request inside GlobalExceptionHandler
