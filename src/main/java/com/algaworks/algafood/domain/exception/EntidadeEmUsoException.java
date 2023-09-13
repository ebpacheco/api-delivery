package com.algaworks.algafood.domain.exception;

//O HttpStatus está sendo tratado no ApiExcpetionController
//@ResponseStatus(value = HttpStatus.CONFLICT)
public class EntidadeEmUsoException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoException(String mensagem) {
		super(mensagem);
	}
}
