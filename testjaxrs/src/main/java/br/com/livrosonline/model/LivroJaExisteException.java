package br.com.livrosonline.model;

public class LivroJaExisteException extends RuntimeException {

	public LivroJaExisteException() {
	}

	public LivroJaExisteException(String message) {
		super(message);
	}

	public LivroJaExisteException(Throwable cause) {
		super(cause);
	}

	public LivroJaExisteException(String message, Throwable cause) {
		super(message, cause);
	}

	public LivroJaExisteException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
