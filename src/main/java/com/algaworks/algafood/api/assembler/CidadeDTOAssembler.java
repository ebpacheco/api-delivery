package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.controller.CidadeController;
import com.algaworks.algafood.api.controller.EstadoController;
import com.algaworks.algafood.api.model.CidadeDTO;
import com.algaworks.algafood.domain.model.Cidade;

@Component
public class CidadeDTOAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeDTO> {

	@Autowired
	private ModelMapper modelMapper;

	public CidadeDTOAssembler() {
		super(CidadeController.class, CidadeDTO.class);
	}

	@Override
	public CidadeDTO toModel(Cidade cidade) {
		CidadeDTO cidadeModel = modelMapper.map(cidade, CidadeDTO.class);
		cidadeModel.add(
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CidadeController.class).buscar(cidadeModel.getId()))
						.withRel("cidade"));
		cidadeModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CidadeController.class).listar())
				.withRel("cidades"));
		cidadeModel.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(EstadoController.class).buscar(cidadeModel.getEstado().getId()))
				.withRel("estado"));
		return cidadeModel;
	}

	@Override
	public CollectionModel<CidadeDTO> toCollectionModel(Iterable<? extends Cidade> cidades) {
		return super.toCollectionModel(cidades).add(WebMvcLinkBuilder.linkTo(CidadeController.class).withSelfRel());
	}

	public List<CidadeDTO> toCollectionDTO(List<Cidade> cidades) {

		return cidades.stream().map(cidade -> toModel(cidade)).collect(Collectors.toList());
	}

}
