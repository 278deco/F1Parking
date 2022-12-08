package fr.f1parking.core.entities;

import fr.f1parking.core.entities.placement.Direction;
import fr.f1parking.core.io.IOHandler;

public class EntityPlayer extends Car {
	
	/**
	 * Create a new Entity player
	 * <p>The player have hard-coded parameter: The facing direction is
	 * EAST and the texture used is player's texture</p>
	 */
	public EntityPlayer() {
		super(Direction.EAST, IOHandler.getInstance().getTexturesFile().getCarTexture(IOHandler.getInstance().getConfiguration().getPlayerCar()));
	}
	
}
