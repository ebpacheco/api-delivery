package com.algaworks.algafood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.GrupoDTO;
import com.algaworks.algafood.domain.model.Grupo;

@Component
public class GrupoDTOAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public GrupoDTO toDto(Grupo grupo) {
		return modelMapper.map(grupo, GrupoDTO.class);
	}

	public List<GrupoDTO> toCollectionDTO(Collection<Grupo> grupos) {
		return grupos.stream().map(grupo -> toDto(grupo)).collect(Collectors.toList());
	}

}
