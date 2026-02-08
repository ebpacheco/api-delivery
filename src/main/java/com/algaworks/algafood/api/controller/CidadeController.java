package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.ResourceUriHelper;
import com.algaworks.algafood.api.assembler.CidadeDTOAssembler;
import com.algaworks.algafood.api.assembler.CidadeInputDisassembler;
import com.algaworks.algafood.api.model.CidadeDTO;
import com.algaworks.algafood.api.model.input.CidadeInput;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Cidades")
@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidadeService;

	@Autowired
	private CidadeDTOAssembler cidadeDTOAssembler;

	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler;

	@Operation(summary = "Lista as cidades")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CidadeDTO> listar() {
		return cidadeDTOAssembler.toCollectionDTO(cidadeRepository.findAll());
	}

	@Operation(summary = "Busca uma cidade por ID")
	@GetMapping("/{cidadeId}")
	public CidadeDTO buscar(@Parameter(description = "ID de uma cidade", example = "1") @PathVariable Long cidadeId) {
		Cidade cidade = cadastroCidadeService.buscarOuFalhar(cidadeId);

		CidadeDTO cidadeDTO = cidadeDTOAssembler.toDTO(cidade);

		cidadeDTO.add(
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CidadeController.class).buscar(cidadeDTO.getId()))
						.withRel("cidade"));
//		cidadeDTO.add(WebMvcLinkBuilder.linkTo(CidadeController.class).slash(cidadeDTO.getId()).withSelfRel());
//		cidadeDTO.add(Link.of("http://localhost:8080/cidades/1"));

		cidadeDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CidadeController.class).listar())
				.withRel("cidades"));
//		cidadeDTO.add(WebMvcLinkBuilder.linkTo(CidadeController.class).withRel("cidades"));
//		cidadeDTO.add(Link.of("http://localhost:8080/cidades", "cidades"));

		cidadeDTO.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(EstadoController.class).buscar(cidadeDTO.getEstado().getId()))
				.withRel("estado"));
//		cidadeDTO.getEstado().add(
//				WebMvcLinkBuilder.linkTo(EstadoController.class).slash(cidadeDTO.getEstado().getId()).withSelfRel());
//		cidadeDTO.getEstado().add(Link.of("http://localhost:8080/estados", "estados"));

		return cidadeDTO;
	}

	@Operation(summary = "Cadastra uma cidade")
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public CidadeDTO adicionar(
			@Parameter @Schema(description = "Dados da nova cidade a ser cadastrada") @RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);

			cidade = cadastroCidadeService.salvar(cidade);

			CidadeDTO cidadeDTO = cidadeDTOAssembler.toDTO(cidade);

			ResourceUriHelper.addUriResponseHeader(cidadeDTO.getId());

			return cidadeDTO;

		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@Operation(summary = "Atualiza uma cidade por ID")
	@PutMapping("/{cidadeId}")
	public CidadeDTO atualizar(@Parameter(description = "ID de uma cidade", example = "1") @PathVariable Long cidadeId,
			@Parameter @Schema(description = "Dados atualizados da cidade") @RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidadeAtual = cadastroCidadeService.buscarOuFalhar(cidadeId);
			cidadeInputDisassembler.copyToDomainObject(cidadeAtual, cidadeInput);
//			BeanUtils.copyProperties(cidade, cidadeAtual, "id");
			return cidadeDTOAssembler.toDTO(cadastroCidadeService.salvar(cidadeAtual));
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@Operation(summary = "Exclui uma cidade por ID")
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@Parameter(description = "ID de uma cidade", example = "1") @PathVariable Long cidadeId) {
		cadastroCidadeService.excluir(cidadeId);
	}
}
