package com.algaworks.algafood.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EstadoIdDTO {
	
	@NotNull
	private Long id;
}
