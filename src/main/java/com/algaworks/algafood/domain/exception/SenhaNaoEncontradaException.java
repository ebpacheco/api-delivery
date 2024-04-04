package com.algaworks.algafood.domain.exception;

public class SenhaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public SenhaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public SenhaNaoEncontradaException(Long usuarioId) {
		this(String.format("Existe um erro na atualizacao do codigo %d", usuarioId));
	}

}
