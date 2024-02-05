package com.algaworks.algafood;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaIT {

	@LocalServerPort
	private int port;

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";

	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		RestAssured.given().accept(ContentType.JSON)
				.when().get()
				.then().statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveRetornar4Cozinhas_QuandoConsultarCozinhas() {
		RestAssured.given().accept(ContentType.JSON)
				.when().get()
				.then().body("id", Matchers.hasSize(4))
				.body("nome", Matchers.hasItems("Indiana", "Brasileira"));
	}
}
