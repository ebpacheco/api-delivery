package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.ProdutoDTOAssembler;
import com.algaworks.algafood.api.assembler.ProdutoInputDisassembler;
import com.algaworks.algafood.api.model.ProdutoDTO;
import com.algaworks.algafood.api.model.input.ProdutoInput;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.service.CadastroProdutoService;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutosController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CadastroProdutoService cadastroProdutoService;

	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;

	@Autowired
	private ProdutoDTOAssembler produtoDTOAssembler;

	@Autowired
	private ProdutoInputDisassembler produtoInputDisassembler;

	@GetMapping
	public List<ProdutoDTO> listar(@PathVariable Long restauranteId,
			@RequestParam(required = false) boolean incluirInativo) {
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

		List<Produto> todosProdutos;
		if (incluirInativo) {
			todosProdutos = produtoRepository.findByRestaurante(restaurante);
		} else {
			todosProdutos = produtoRepository.findAtivosByRestaurante(restaurante);

		}
		return produtoDTOAssembler.toCollectionDTO(todosProdutos);
	}

	@GetMapping(value = "/{produtoId}")
	public ProdutoDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		Produto produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);
		return produtoDTOAssembler.toDTO(produto);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoDTO adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
		produto.setRestaurante(restaurante);
		produto = cadastroProdutoService.salvar(produto);
		return produtoDTOAssembler.toDTO(produto);
	}

	@PutMapping(value = "/{produtoId}")
	public ProdutoDTO atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@RequestBody @Valid ProdutoInput produtoInput) {
		Produto produtoAtual = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);
		produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);
		produtoAtual = cadastroProdutoService.salvar(produtoAtual);
		return produtoDTOAssembler.toDTO(produtoAtual);

	}

}
