package fr.f1parking.core.exceptions;

import java.util.Optional;
import java.util.UUID;

public class EntityException extends RuntimeException {
	
	private static final long serialVersionUID = -717663932130582393L;
	
	public EntityException() {
		super();
	}
	
	public EntityException(Optional<UUID> entityID) {
		super(entityID.isPresent() ? " [Entity UUID: "+entityID.get()+"]" : "");
	}
	
	public EntityException(String exception, Optional<UUID> entityID) {
		super(exception+(entityID.isPresent() ? " [Entity UUID: "+entityID.get()+"]" : ""));
	}
	
	
}
