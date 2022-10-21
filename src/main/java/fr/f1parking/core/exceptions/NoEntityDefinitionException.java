package fr.f1parking.core.exceptions;

import java.util.Optional;

public class NoEntityDefinitionException extends EntityException {

	private static final long serialVersionUID = -5332710861126495326L;
	
	public NoEntityDefinitionException() {
		super();
	}
	
	public NoEntityDefinitionException(String exception) {
		super(exception, Optional.empty());
	}

}
