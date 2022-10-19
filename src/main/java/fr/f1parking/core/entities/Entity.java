package fr.f1parking.core.entities;

import java.util.UUID;

import fr.f1parking.core.entities.placement.Direction;

public abstract class Entity {
	
	protected UUID id;
	protected final Direction facingDirection;
	
	public Entity(Direction dir) {
		this.id = UUID.randomUUID();
		this.facingDirection = dir;
	}
	
	public UUID getId() {
		return id;
	}
	
	public Direction getFacingDirection() {
		return facingDirection;
	}
	
	public abstract int getSize();
}
