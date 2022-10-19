package fr.f1parking.core.entities;

import fr.f1parking.core.entities.placement.Direction;

public class Car extends Entity {

	public Car(Direction dir) {
		super(dir);
	}

	@Override
	public int getSize() {
		return 2;
	}

}
