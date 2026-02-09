package com.algaworks.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioDTO extends RepresentationModel<UsuarioDTO> {

	private Long id;
	private String nome;
	private String email;

}
