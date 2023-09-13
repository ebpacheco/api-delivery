package com.algaworks.algafood.domain.exception;

//O HttpStatus está sendo tratado no ApiExcpetionController
//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public abstract class EntidadeNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
}
