package br.com.livrosonline.model;

public class LivroNaoEncontradoException extends RuntimeException {

	public LivroNaoEncontradoException() {

	}

	public LivroNaoEncontradoException(String message) {
		super(message);

	}

	public LivroNaoEncontradoException(Throwable cause) {
		super(cause);

	}

	public LivroNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);

	}

	//Construtor JDK 1.7
	public LivroNaoEncontradoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
