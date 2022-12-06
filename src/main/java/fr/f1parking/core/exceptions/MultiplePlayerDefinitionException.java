package fr.f1parking.core.exceptions;

import java.util.Optional;
import java.util.UUID;

public class MultiplePlayerDefinitionException extends EntityException {

	private static final long serialVersionUID = -437456054493493657L;

	public MultiplePlayerDefinitionException() {
		super();
	}
	
	public MultiplePlayerDefinitionException(String exception, Optional<UUID> entityID) {
		super(exception, entityID);
	}
}
