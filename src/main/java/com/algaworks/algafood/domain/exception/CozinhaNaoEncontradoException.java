package com.algaworks.algafood.domain.exception;

//O HttpStatus está sendo tratado no ApiExcpetionController
//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CozinhaNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CozinhaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public CozinhaNaoEncontradoException(Long cozinhaId) {
		this(String.format("Nao existe um cadastro de cozinha com o codigo %d", cozinhaId));
	}
}
