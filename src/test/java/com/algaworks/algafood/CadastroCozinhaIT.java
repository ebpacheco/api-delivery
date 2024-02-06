package com.algaworks.algafood;

import static org.hamcrest.CoreMatchers.equalTo;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIT {

	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	private CozinhaRepository cozinhaRepository;

//	@Autowired
//	private Flyway flyway;

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";

		databaseCleaner.clearTables();
		preparaDados();

//		flyway.migrate();
	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		RestAssured.given().accept(ContentType.JSON).when().get().then().statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveRetornar2Cozinhas_QuandoConsultarCozinhas() {
		RestAssured.given().accept(ContentType.JSON).when().get().then().body("id", Matchers.hasSize(2)).body("nome",
				Matchers.hasItems("Indiana", "Brasileira"));
	}

	@Test
	public void deveRetornarStatus201_QuandoCadastrarCozinha() {
		RestAssured.given().body("{ \"nome\" : \"Chinesa\" }").contentType(ContentType.JSON).accept(ContentType.JSON)
				.when().post().then().statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoCozinhaExistente() {
		RestAssured.given().pathParam("cozinhaId", 2).accept(ContentType.JSON)
		.when().get("/{cozinhaId}")
		.then().statusCode(HttpStatus.OK.value()).body("nome", equalTo("Brasileira"));
	}
	
	@Test
	public void deveRetornarStatus404_QuandoCozinhaInexistente() {
		RestAssured.given().pathParam("cozinhaId", 100).accept(ContentType.JSON)
		.when().get("/{cozinhaId}")
		.then().statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	private void preparaDados() {
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Indiana");
		cozinhaRepository.save(cozinha1);

		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Brasileira");
		cozinhaRepository.save(cozinha2);
	}
}
