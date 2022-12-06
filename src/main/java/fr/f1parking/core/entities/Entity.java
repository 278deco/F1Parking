package fr.f1parking.core.entities;

import java.util.UUID;

import fr.f1parking.core.entities.placement.Direction;
import fr.f1parking.ui.render.Texture;

/**
 * An abstract representation of an object on the GridMap
 * @author 278deco
 */
public abstract class Entity {
	
	protected UUID id;
	protected final Direction facingDirection;
	protected final Texture texture;
	
	/**
	 * Create a new Entity
	 * @param dir - the facing direction of the entity
	 * @param texture - the texture used by the entity
	 */
	public Entity(Direction dir, Texture texture) {
		this.id = UUID.randomUUID();
		this.facingDirection = dir;
		this.texture = texture;
	}
	
	/**
	 * Return an unique ID used to identified the entity on the grid
	 * @return an UUID
	 */
	public UUID getId() {
		return id;
	}
	
	/**
	 * Return the facing direction of the entity
	 * @return a direction
	 */
	public Direction getFacingDirection() {
		return facingDirection;
	}
	
	/**
	 * Return the texture's name used by the car
	 * @return the texture name
	 */
	public Texture getTextureName() {
		return this.texture;
	}
	
	/**
	 * Return the size of the entity (the number of boxes used to store the entity in the grid)
	 * @return the entity's size
	 */
	public abstract int getSize();
	
	/**
	 * Compare two entities and tell if they are equals or not
	 * <p> Points of comparaison : </br>
	 * The entity's size </br>
	 * The entity's facing direction </br>
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		return obj instanceof Entity ? isEqual((Entity)obj) : false;
	}
	
	private boolean isEqual(Entity e) {
		return e.id.equals(this.id) &&
				e.facingDirection.equals(this.facingDirection) &&
				e.getSize() == this.getSize();
	}
}
