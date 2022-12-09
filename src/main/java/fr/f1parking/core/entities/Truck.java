package fr.f1parking.core.entities;

import fr.f1parking.core.entities.placement.Direction;
import fr.f1parking.ui.render.Texture;

public class Truck extends Entity {

	/**
	 * Create a new Truck object
	 * @param dir - the facing direction of the entity
	 * @param texture - the texture used by the entity
	 * @see fr.f1parking.core.entities.Entity
	 */
	public Truck(Direction dir, Texture texture) {
		super(dir, texture);
	}

	@Override
	public int getSize() {
		return 3;
	}

}
