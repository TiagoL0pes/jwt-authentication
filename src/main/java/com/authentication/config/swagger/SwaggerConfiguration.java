package com.authentication.config.swagger;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
	@Bean
	public Docket documentacao() {
		return  new Docket(DocumentationType.SWAGGER_2)
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.authentication"))
					.paths(PathSelectors.ant("/**"))
					.build()
					.apiInfo(ApiInfo())
					.globalOperationParameters(globalOperationParameters());
	}
	
	private ApiInfo ApiInfo() {
		return new ApiInfo(
				"API Documentation", 
				"", 
				"v1", 
				"", 
				new Contact("", "", ""), 
				"", 
				"", 
				Collections.emptyList());
		
	}

	private List<Parameter> globalOperationParameters() {
		return Arrays.asList(
				new ParameterBuilder()
				.name("Autorization")
				.description("JWT Header")
				.modelRef(new ModelRef("string"))
				.parameterType("header")
				.required(false)
				.build());
	}

}
