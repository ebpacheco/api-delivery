package com.algaworks.algafood;

import static org.hamcrest.CoreMatchers.equalTo;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.util.DatabaseCleaner;
import com.algaworks.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroRestauranteIT {

	@LocalServerPort
	private int port;
	private String jsonCorretoRestauranteSantaCoxinha;
	private String jsonIncorretoRestauranteSemTaxa;
	private String jsonIncorretoRestauranteSemCozinha;
	private String jsonIncorretoRestauranteComCozinhaInexistente;
	private Restaurante restauranteCarasi;
	private static final int RESTAURANTE_ID_INEXISTENTE = 1000;
	private static final String VIOLACAO_DE_REGRA_DE_NEGOCIO_PROBLEM_TYPE = "Violação de regra de negócio";
	private static final String DADOS_INVALIDOS_PROBLEM_TITLE = "Dados inválidos";

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private RestauranteRepository restauranteRepository;

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/restaurantes";

		jsonCorretoRestauranteSantaCoxinha = ResourceUtils
				.getContentFromResource("/json/correto/restaurante-santaCoxinha.json");

		jsonIncorretoRestauranteSemTaxa = ResourceUtils
				.getContentFromResource("/json/incorreto/restaurante-semTaxaFrete.json");

		jsonIncorretoRestauranteSemCozinha = ResourceUtils
				.getContentFromResource("/json/incorreto/restaurante-semCozinha.json");

		jsonIncorretoRestauranteComCozinhaInexistente = ResourceUtils
				.getContentFromResource("/json/incorreto/restaurante-comCozinhaInexistente.json");

		databaseCleaner.clearTables();

		prepararDados();

	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarRestaurantes() {
		RestAssured.given().accept(ContentType.JSON).when().get().then().statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveRetornarStatus201_QuandoCadastrarRestaurante() {
		RestAssured.given().body(jsonCorretoRestauranteSantaCoxinha).contentType(ContentType.JSON)
				.accept(ContentType.JSON).when().post().then().statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void deveRetornarStatus400_QuandoCadastrarRestauranteSemTaxaFrete() {
		RestAssured.given().body(jsonIncorretoRestauranteSemTaxa).contentType(ContentType.JSON).accept(ContentType.JSON)
				.when().post().then().statusCode(HttpStatus.BAD_REQUEST.value())
				.body("title", equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));
	}

	@Test
	public void deveRetornarStatus400_QuandoCadastrarRestauranteSemCozinha() {
		RestAssured.given().body(jsonIncorretoRestauranteSemCozinha).contentType(ContentType.JSON)
				.accept(ContentType.JSON).when().post().then().statusCode(HttpStatus.BAD_REQUEST.value())
				.body("title", equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));
	}

	@Test
	public void deveRetornarStatus400_QuandoCadastrarRestauranteComCozinhaInexistente() {
		RestAssured.given().body(jsonIncorretoRestauranteComCozinhaInexistente).contentType(ContentType.JSON)
				.accept(ContentType.JSON).when().post().then().statusCode(HttpStatus.BAD_REQUEST.value())
				.body("title", equalTo(VIOLACAO_DE_REGRA_DE_NEGOCIO_PROBLEM_TYPE));
	}

	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarRestauranteExistente() {
		RestAssured.given().pathParam("restauranteId", restauranteCarasi.getId()).accept(ContentType.JSON).when()
				.get("/{restauranteId}").then().statusCode(HttpStatus.OK.value())
				.body("nome", equalTo(restauranteCarasi.getNome()));
	}

	@Test
	public void deveRetornarStatus404_QuandoConsultarRestauranteInexistente() {
		RestAssured.given().pathParam("restauranteId", RESTAURANTE_ID_INEXISTENTE).accept(ContentType.JSON).when()
				.get("/{restauranteId}").then().statusCode(HttpStatus.NOT_FOUND.value());
	}

	private void prepararDados() {
		Cozinha cozinhaBrasileira = new Cozinha();
		cozinhaBrasileira.setNome("Brasileira");
		cozinhaRepository.save(cozinhaBrasileira);

		restauranteCarasi = new Restaurante();
		restauranteCarasi.setNome("Carasi");
		restauranteCarasi.setTaxaFrete(new BigDecimal(8));
		restauranteCarasi.setCozinha(cozinhaBrasileira);
		restauranteRepository.save(restauranteCarasi);

	}
}
