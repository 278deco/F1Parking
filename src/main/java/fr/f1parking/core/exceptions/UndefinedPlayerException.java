package fr.f1parking.core.exceptions;

import java.util.Optional;

public class UndefinedPlayerException extends EntityException {

	private static final long serialVersionUID = 85760568174329873L;
	
	public UndefinedPlayerException() {
		super();
	}
	
	public UndefinedPlayerException(String exception) {
		super(exception, Optional.empty());
	}

}
