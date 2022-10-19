package fr.f1parking.core.entities;

import fr.f1parking.core.entities.placement.Direction;

public class Truck extends Entity {

	public Truck(Direction dir) {
		super(dir);
	}

	@Override
	public int getSize() {
		return 3;
	}

}
