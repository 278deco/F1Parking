package fr.f1parking.core.level.gen.generators;

import fr.f1parking.core.entities.placement.Direction;
import fr.f1parking.core.helpers.MathHelper;
import fr.f1parking.core.level.gen.IGenerator;

public class ManualPlacingGenerator implements IGenerator {
	
	private final byte[][] grid;
	
	private ManualPlacingGenerator(Builder b) {
		this.grid = b.getgrid();
	}
	
	public byte getPos(byte x, byte y) {
		return grid[y][x];
	}
	
	public static ManualPlacingGenerator.Builder builder() {
		return new ManualPlacingGenerator.Builder();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		return obj instanceof ManualPlacingGenerator ? isEqual((ManualPlacingGenerator)obj) : false;
	}
	
	private boolean isEqual(ManualPlacingGenerator mpg) {
		return mpg.grid != null && this.grid != null 
				&& mpg.grid.equals(this.grid);
	}
	
	public static final class Builder {
		
		private final byte[][] grid;
		
		private Builder() {
			grid = new byte[GRID_SIZE][GRID_SIZE];
		}
		
		/**
		 * Place an object on the map
		 * <p>The object is a number representation for an entity: 1 for an random entity, 2 for the player<br> 
		 * The size of the object is either 2 for a Car or 3 for a Truck<br>
		 * The rotation (or Direction) indicate where the entity will be facing<br></p>
		 * @param x - the X position where the object will be placed
		 * @param y - the Y position where the object will be placed
		 * @param obj - the object to place 
		 * @param size - the size of the object
		 * @param rot - the rotation of the object
		 * @see fr.f1parking.core.entities.placement.Direction
		 * @return this instance
		 */
		public Builder placement(byte x, byte y, byte obj, byte size, byte rot) {
			if(size < 1 || (obj < 0 && obj > 2) || (rot < 0 && rot > 3)) throw new IllegalArgumentException();
			if(MathHelper.bitCount(obj) > 2 || MathHelper.bitCount(size) > 2 || MathHelper.bitCount(rot) > 4) throw new IllegalArgumentException("Unable to perform a byte concatenation");
			if(x >= GRID_SIZE || y >= GRID_SIZE || x < 0 || y < 0) throw new IllegalArgumentException("x and y arguments cannot be greater than "+GRID_SIZE+" or lower than 0");
			this.grid[y][x] = (byte)((obj<<6)+(size<<4)+rot);
			
			return this;
		}
		
		/**
		 * Place an object on the map
		 * <p>The object is a number representation for an entity: 1 for an random entity, 2 for the player<br> 
		 * The size of the object is either 2 for a Car or 3 for a Truck<br>
		 * The rotation (or Direction) indicate where the entity will be facing<br></p>
		 * @param x - the X position where the object will be placed
		 * @param y - the Y position where the object will be placed
		 * @param obj - the object to place 
		 * @param size - the size of the object
		 * @param rot - the rotation of the object
		 * @see fr.f1parking.core.entities.placement.Direction
		 * @return this instance
		 */
		public Builder placement(int x, int y, int obj, int size, int rot) {
			return placement((byte)x, (byte)y, (byte)obj, (byte)size, (byte)rot);
		}
		
		/**
		 * Place an object on the map
		 * <p>The object is a number representation for an entity: 1 for an random entity, 2 for the player<br> 
		 * The size of the object is either 2 for a Car or 3 for a Truck<br>
		 * The rotation (or Direction) indicate where the entity will be facing<br></p>
		 * @param x - the X position where the object will be placed
		 * @param y - the Y position where the object will be placed
		 * @param obj - the object to place 
		 * @param size - the size of the object
		 * @param rot - the rotation of the object
		 * @see fr.f1parking.core.entities.placement.Direction
		 * @return this instance
		 */
		public Builder placement(int x, int y, int obj, int size, Direction rot) {
			return placement((byte)x, (byte)y, (byte)obj, (byte)size, rot.getValue());
		}
		
		public ManualPlacingGenerator build() {
			return new ManualPlacingGenerator(this);
		}
		
		private byte[][] getgrid() {
			return this.grid;
		}
		
	}
	
}
