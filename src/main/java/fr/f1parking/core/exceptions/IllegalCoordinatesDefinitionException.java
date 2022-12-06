package fr.f1parking.core.exceptions;

public class IllegalCoordinatesDefinitionException extends RuntimeException {

	private static final long serialVersionUID = -1485697890376351722L;

	public IllegalCoordinatesDefinitionException() {
		super();
	}
	
	public IllegalCoordinatesDefinitionException(String msg) {
		super(msg);
	}
}
