package com.algaworks.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.api.assembler.FotoProdutoDTOAssembler;
import com.algaworks.algafood.api.model.FotoProdutoDTO;
import com.algaworks.algafood.api.model.input.FotoProdutoInput;
import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.service.CadastroProdutoService;
import com.algaworks.algafood.domain.service.CatalogoFotoProdutoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteFotoProdutoController {

	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProdutoService;

	@Autowired
	private CadastroProdutoService cadastroProdutoService;

	@Autowired
	private FotoProdutoDTOAssembler fotoProdutoDTOAssembler;

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoDTO atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@Valid FotoProdutoInput fotoProdutoInput) {
//		var nomeArquivo = UUID.randomUUID() + "_" + fotoProdutoInput.getArquivo().getOriginalFilename();
//		var arquivoFoto = Path.of("C:\\Users\\DudatraK\\OneDrive\\Documentos\\projeto-algafood-fotos\\catalogo",
//				nomeArquivo);
//		try {
//			fotoProdutoInput.getArquivo().transferTo(arquivoFoto);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}

		Produto produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);
		MultipartFile arquivo = fotoProdutoInput.getArquivo();

		FotoProduto foto = new FotoProduto();
		foto.setProduto(produto);
		foto.setDescricao(fotoProdutoInput.getDescricao());
		foto.setContentType(arquivo.getContentType());
		foto.setTamanho(arquivo.getSize());
		foto.setNomeArquivo(arquivo.getOriginalFilename());
		FotoProduto fotoSalva = catalogoFotoProdutoService.salvar(foto);
		return fotoProdutoDTOAssembler.toDTO(fotoSalva);

	}

}
