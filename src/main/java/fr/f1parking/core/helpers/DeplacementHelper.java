package fr.f1parking.core.helpers;

import fr.f1parking.core.entities.placement.Coordinate;
import fr.f1parking.core.entities.placement.Direction;
import fr.f1parking.core.level.gen.IGenerator;

public class DeplacementHelper {

	/**
	 * Check if the direction wanted by the user matches the possible
	 * movement of the car
	 * 
	 * @param playerDir - the player's facing direction
	 * @param wantedDir - the user desired movement
	 * @return if the direction is valid
	 */
	public static boolean isAValidDirection(Direction playerDir, Direction wantedDir) {
		boolean ret = false;
		switch (playerDir) {
			case NORTH:
				ret = wantedDir == Direction.SOUTH;
				break;
			case SOUTH:
				ret = wantedDir == Direction.NORTH;
				break;
			case EAST:
				ret = wantedDir == Direction.WEST;
				break;
			case WEST:
				ret = wantedDir == Direction.EAST;
				break;
			default:
				ret = false;
		}
		return ret || wantedDir == playerDir;
	}
	
	/**
	 * Check if the movement is valid meaning if the direction correspond to
	 * the car's facing direction and if not out of bound
	 * 
	 * @param playerCoord - player's coordinates
	 * @param playerDir - player's facing direction
	 * @param wantDir - the user desired movement
	 * @return if the movement is possible
	 */
	public static boolean isAValidMovement(Coordinate playerCoord, Direction playerDir, Direction wantDir) {
		boolean ret = isAValidDirection(playerDir, wantDir);
		
		return ret || playerCoord.withinInterval(new Coordinate(1,1), new Coordinate(IGenerator.GRID_SIZE-1, IGenerator.GRID_SIZE-1));
	}
	
	/**
	 * Create an array of Coordinate when moving the entity. Each of them indicate a important box of the car:
	 * <p>- the new box of the car to be created <br>
	 * - the old box to be erased <br>
	 * - the new root box to be created <br>
	 * - the old root box to be erased </p>
	 * 
	 * @param eCoord - Erased box coordinates
	 * @param movingDirection - where the entity will be moved
	 * @param facingDirection - where the entity is facing
	 * @param size - the entity's size
	 * @return an array of coordinates
	 */
	public static Coordinate[] getEntityMovingBoxes(Coordinate eCoord, Direction movingDirection, Direction facingDirection, byte size) {
		Coordinate createdBox;
		Coordinate erasedBox;
		Coordinate rootBox;
		Coordinate oldRootBox;
		
		if(movingDirection == facingDirection) {
			createdBox = new Coordinate(size, size).times(movingDirection.getShiftCoordinates()).add(eCoord);
			rootBox = new Coordinate(1,1).times(movingDirection.getShiftCoordinates()).add(eCoord);
			erasedBox = eCoord;
			oldRootBox = null;
		}else {
			createdBox = new Coordinate(1,1).times(movingDirection.getShiftCoordinates()).add(eCoord);
			rootBox = new Coordinate(1,1).times(movingDirection.getShiftCoordinates()).add(eCoord);
			erasedBox = new Coordinate(size-1, size-1).times(facingDirection.getShiftCoordinates()).add(eCoord);
			oldRootBox = eCoord;
		}

		return new Coordinate[] {rootBox, createdBox, erasedBox, oldRootBox};
	}
	
	/**
	 * Calculate object's size will take in two dimensions (X and Y) depending on the rotation
	 * This method is static : will only return a static size (max size) for X and Y coordinates
	 * 
	 * @see fr.f1parking.core.helpers.DeplacementHelper#getDynamicXYValueForDirection(byte, byte, Direction)
	 * @param size - the size of the object
	 * @param rot - the rotation of the object
	 * @return a tuple containing the size take in X and Y
	 */
	public static Coordinate getXYValueForDirection(byte size, Direction rot) {
		return getDynamicXYValueForDirection(size, (byte)0, rot);
	}
	
	/**
	 * Calculate object's size will take in two dimensions (X and Y) depending on the rotation
	 * This method is dynamic: will only return a dynamic size depending on i value for X and Y coordinates
	 * 
	 * @param size - the size of the object
	 * @param i - an iterator number, while decreased the size of the object to return a dynamic size depending on i size (cannot be bigger than size)
	 * @param rot - the rotation of the object
	 * @throws IllegalArgumentException if the i parameter is greater than size parameter
	 * @return a tuple containing the size take in X and Y
	 */
	public static Coordinate getDynamicXYValueForDirection(byte size, byte i, Direction rot) {
		if(i > size) throw new IllegalArgumentException("The i parameter cannot be greater than the size of the object");
		if(rot == Direction.NULL) throw new NullPointerException("The direction cannot be null");
		
		//If dir == NORTH or SOUTH: dirX = 0 else if dir == WEST: dirX = size * -1 else (dir == EAST) dirX = size*1
		byte dirX = (byte)((rot == Direction.NORTH || rot == Direction.SOUTH) ? 0 : ((size-i) * ((rot == Direction.WEST) ? -1 : 1)));
		
		//If dir == WEST or EAST: dirY = 0 else if dir == NORTH: dirX = size * -1 else (dir == SOUTH) dirX = size*1
		byte dirY = (byte)((rot == Direction.WEST || rot == Direction.EAST) ? 0 : ((size-i) * ((rot == Direction.NORTH) ? -1 : 1)));
		
		return new Coordinate(dirX, dirY);
	}
	
}
