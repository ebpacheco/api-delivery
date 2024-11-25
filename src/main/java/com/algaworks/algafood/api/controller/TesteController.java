package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

//@RestController
//@RequestMapping(path = "/teste")
public class TesteController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private RestauranteRepository restauranteRepository;

	@GetMapping("/nome")
	public List<Cozinha> cozinhasPorNome(@RequestParam String nome) {
		return cozinhaRepository.findTodosByNomeContaining(nome);
	}

	@GetMapping("/nome-unico")
	public Optional<Cozinha> cozinhaPorNome(@RequestParam String nome) {
		return cozinhaRepository.findByNome(nome);
	}

	@GetMapping("/exists")
	public boolean cozinhaExists(@RequestParam String nome) {
		return cozinhaRepository.existsCozinhaByNome(nome);
	}

	@GetMapping("/taxaFrete")
	public List<Restaurante> restaurantesPorTaxaFrete(@RequestParam BigDecimal taxaInicial,
			@RequestParam BigDecimal taxaFinal) {
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}

	@GetMapping("/taxaFrete2")
	public List<Restaurante> restaurantesPorTaxaFrete2(@RequestParam String nome,
			@RequestParam("taxaInicial") BigDecimal taxaFreteInicial,
			@RequestParam("taxaFinal") BigDecimal taxaFreteFinal) {
		return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
	}

//	@GetMapping("/taxaFrete3")
//	public List<Restaurante> restaurantesPorTaxaFrete3(){
//		return restauranteRepository.find();
//	}

	@GetMapping("/nome-id")
	public List<Restaurante> restaurantesPorNomeEId(@RequestParam String nome, @RequestParam Long cozinha) {
		return restauranteRepository.consultarPorNome(nome, cozinha);
	}

	@GetMapping("/nome-primeiro")
	public Optional<Restaurante> primeiroRestaurante(@RequestParam String nome) {
		return restauranteRepository.findFirstRestauranteByNomeContaining(nome);
	}

	@GetMapping("/top2")
	public List<Restaurante> top2Restaurantes(@RequestParam String nome) {
		return restauranteRepository.findTop2RestauranteByNomeContaining(nome);
	}

	@GetMapping("/count-cozinha")
	public int countCozinha(@RequestParam Long cozinhaId) {
		return restauranteRepository.countByCozinhaId(cozinhaId);
	}

	@GetMapping("/com-frete-gratis")
	public List<Restaurante> restauranteComFreteGratis(String nome) {
		return restauranteRepository.findComFreteGratis(nome);
	}

	@GetMapping("/restaurante-primeiro")
	public Optional<Restaurante> restaurantePrimeiro() {
		return restauranteRepository.buscarPrimeiro();
	}

	@GetMapping("/cozinha-primeiro")
	public Optional<Cozinha> cozinhaPrimeiro() {
		return cozinhaRepository.buscarPrimeiro();
	}
}
