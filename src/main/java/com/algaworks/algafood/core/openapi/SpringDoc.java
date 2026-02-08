package com.algaworks.algafood.core.openapi;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
@OpenAPIDefinition
public class SpringDoc implements WebMvcConfigurer {

	// http://localhost:8080/swagger-ui/index.html#/

	@Bean
	GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().group("public").packagesToScan("com.algaworks.algafood.api")
//				.pathsToMatch("/restaurantes/*")
				.build();
	}

	@Bean
	OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("AlgaFood API").description("API aberta para clientes e restaurantes")
						.version("v1").license(new License().name("Apache 2.0").url("http://springdoc.org")))
				.externalDocs(new ExternalDocumentation().description("SpringShop Wiki Documentation")
						.url("https://springshop.wiki.github.org/docs"))
				.addTagsItem(new Tag().name("Cidades").description("Gerencie as cidades"));

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}