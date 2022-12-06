package fr.f1parking.core.entities;

import fr.f1parking.core.entities.placement.Direction;
import fr.f1parking.ui.render.Texture;

public class Car extends Entity {

	/**
	 * Create a new Car
	 * @param dir - the facing direction of the entity
	 * @param texture - the texture used by the entity
	 * @see fr.f1parking.core.entities.Entity
	 */
	public Car(Direction dir, Texture t) {
		super(dir, t);
	}

	@Override
	public int getSize() {
		return 2;
	}

}
