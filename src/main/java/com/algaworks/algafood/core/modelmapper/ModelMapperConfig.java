package com.algaworks.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();

		// Alternativa para usar precoFrete em vez de taxaFrete...O modelMapper nao tem
		// a inteligencia de identificar mas podemos fazer o mapeamento abaixo
//		ModelMapper modelMapper = new ModelMapper();
//		modelMapper.createTypeMap(Restaurante.class, RestauranteDTO.class).addMapping(Restaurante::getTaxaFrete,
//				RestauranteDTO::setPrecoFrete);
//		return modelMapper;
	}
}
