package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.PermissaoDTO;
import com.algaworks.algafood.domain.model.Permissao;

@Component
public class PermissaoDTOAssembler {

	@Autowired
	ModelMapper modelMapper;

	public PermissaoDTO toDto(Permissao permissao) {
		return modelMapper.map(permissao, PermissaoDTO.class);
	}

	public List<PermissaoDTO> toCollectionDTO(Set<Permissao> permissoes) {
		return permissoes.stream().map(permissao -> toDto(permissao)).collect(Collectors.toList());
	}
}
