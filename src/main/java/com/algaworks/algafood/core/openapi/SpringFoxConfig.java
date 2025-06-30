package com.algaworks.algafood.core.openapi;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@Configuration
@OpenAPIDefinition
public class SpringFoxConfig {

    @Bean
    GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().group("springshop-public").pathsToMatch("/public/**").build();
	}
}
