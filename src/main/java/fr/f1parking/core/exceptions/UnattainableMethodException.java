package fr.f1parking.core.exceptions;

public class UnattainableMethodException extends RuntimeException {

	private static final long serialVersionUID = -8791017182225242820L;

	public UnattainableMethodException() {
		super();
	}
	
	public UnattainableMethodException(String exception) {
		super(exception);
	}
	
}
