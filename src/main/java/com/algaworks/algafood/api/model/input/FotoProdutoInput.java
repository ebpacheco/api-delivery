package com.algaworks.algafood.api.model.input;

import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.core.validation.FileSize;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FotoProdutoInput {

	@NotNull
	@FileSize(max = "500KB")
	private MultipartFile arquivo;

	@NotBlank
	private String descricao;
}
