package fr.f1parking.core.maps;

import fr.f1parking.core.entities.Car;
import fr.f1parking.core.entities.Truck;
import fr.f1parking.core.entities.placement.Direction;
import fr.f1parking.core.io.IOHandler;
import fr.f1parking.core.level.gen.IGenerator;
import fr.f1parking.core.level.gen.ILayer;
import fr.f1parking.core.level.gen.generators.ManualPlacingGenerator;
import fr.f1parking.core.level.gen.layers.ManualPlacingLayer;

public class Number1Map extends IMap {

	public Number1Map() {
		this.entities.add(new Truck(Direction.EAST, IOHandler.getInstance().getTexturesFile().getTruckTexture("truck1")));
		this.entities.add(new Truck(Direction.EAST, IOHandler.getInstance().getTexturesFile().getTruckTexture("truck2")));
		this.entities.add(new Truck(Direction.NORTH, IOHandler.getInstance().getTexturesFile().getTruckTexture("truck4")));
		this.entities.add(new Truck(Direction.NORTH, IOHandler.getInstance().getTexturesFile().getTruckTexture("truck3")));
		this.entities.add(new Car(Direction.EAST, IOHandler.getInstance().getTexturesFile().getCarTexture("alpineBlue")));
	}
	
	@Override
	public String getName() {
		return "Map N\u00B01";
	}

	@Override
	public IGenerator getGenerator() {
		return ManualPlacingGenerator.builder()
			.placement(0, 2, 2, 2, Direction.EAST) // Player
			.placement(0, 0, 1, 3, Direction.EAST) // Truck 1
			.placement(3, 0, 1, 3, Direction.EAST) // Truck 2
			.placement(3, 4, 1, 3, Direction.NORTH) // Truck 3
			.placement(4, 4, 1, 2, Direction.EAST) // Car 1
			.placement(5, 3, 1, 3, Direction.NORTH) // Truck 4
			.build();
	}

	@Override
	public ILayer getLayer() {
		return ManualPlacingLayer.builder()
			.entities(this.entities).player(this.player)
			.build();
	}

}
