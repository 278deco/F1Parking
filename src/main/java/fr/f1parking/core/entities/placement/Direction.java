package fr.f1parking.core.entities.placement;

/**
 * Represent a direction 
 * <p> Used to define the rotation of an object 
 * or the direction when moving it</p>
 * @author 278deco
 */
public enum Direction {
	NORTH((byte)0), //ID = 0
	SOUTH((byte)1), //ID = 1
	WEST((byte)2),  //ID = 2
	EAST((byte)3),	//ID = 3
	NULL((byte)-1); //ID = -1 
	
	/**
	 * Get a direction by giving it index
	 * @param v - the byte value of the direction
	 * @return a new direction
	 */
	public static Direction of(byte v) {
		Direction ret = NULL;
		for(Direction dir : Direction.values()) if(dir.getValue() == v) ret = dir;
		return ret;
	}
	
	/**
	 * Get a direction by giving it index
	 * @param v - the int value of the direction
	 * @return a new direction
	 */
	public static Direction of(int v) {
		return of((byte)v);
	}
	
	private byte value;
	private Direction(byte v) {
		this.value = v;
	}
	
	/**
	 * Get the byte value of a direction
	 * @return the byte of the direction
	 */
	public byte getValue() {
		return value;
	}
	
	/**
	 * Get the opposite direction of this object
	 * @return a new direction
	 */
	public Direction getOpposite() {
		return this.getValue() == -1 ? NULL : (this.getValue()% 2 == 0 ? values()[this.getValue()+1] : values()[this.getValue()-1]);
	}
	
	/**
	 * Get the shift implied by the coordinate
	 * @return a new coordinate
	 */
	public Coordinate getShiftCoordinates() {
		switch(this) {
			case NORTH:
				return new Coordinate(0,-1);
			case SOUTH:
				return new Coordinate(0,1);
			case EAST:
				return new Coordinate(1,0);
			case WEST:
				return new Coordinate(-1,0);
			default:
				return new Coordinate(0,0);
		}
	}
	
	public double getRotation() {
		switch(this) {
			case NORTH:
				return 0D;
			case SOUTH:
				return 180D;
			case EAST:
				return 90D;
			case WEST:
				return 270D;
			default:
				return 0D;
		}
	}
}
