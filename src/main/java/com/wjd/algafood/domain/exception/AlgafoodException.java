package com.wjd.algafood.domain.exception;

public class AlgafoodException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public AlgafoodException(String mensagem) {
		super(mensagem);
	}
	
}
