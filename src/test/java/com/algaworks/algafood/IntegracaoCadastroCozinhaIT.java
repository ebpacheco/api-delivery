package com.algaworks.algafood;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest
public class IntegracaoCadastroCozinhaIT {

	@Autowired
	CadastroCozinhaService cadastroCozinhaService;

//	@Test
	public void deveAtribuirId_QuandoCadastraCozinhaComDadosCorretos() {
//		public void testar_cadastro_cozinha_com_sucesso()
		// Cenario
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");

		// Acao
		novaCozinha = cadastroCozinhaService.salvar(novaCozinha);

		// Validacao
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}

	@Test
	public void deveFalhar_QuandoCadastrarCozinhaSemNome() {

		Exception exception = assertThrows(ConstraintViolationException.class, () -> {
			Cozinha novaCozinha = new Cozinha();
			novaCozinha.setNome(null);
			novaCozinha = cadastroCozinhaService.salvar(novaCozinha);
		});

		assertThat(exception);
	}

	@Test
	public void deveFalhar_QuandoExcluirCozinhaEmUso() {

		Exception exception = assertThrows(EntidadeEmUsoException.class, () -> {
			cadastroCozinhaService.excluir(1L);
		});
		assertThat(exception);
	}

	@Test
	public void deveFalhar_QuandoExcluirCozinhaInexistente() {

		Exception exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
			cadastroCozinhaService.excluir(100L);
		});

		assertThat(exception);

	}
}
