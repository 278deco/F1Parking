package fr.f1parking.core.level.gen.generators;

import fr.f1parking.core.entities.placement.Coordinate;
import fr.f1parking.core.entities.placement.Direction;
import fr.f1parking.core.exceptions.UnattainableMethodException;
import fr.f1parking.core.helpers.MathHelper;
import fr.f1parking.core.level.gen.IGenerator;
import fr.f1parking.core.level.gen.backtracking.SeedSelector;

public class ProceduralPlacingGenerator implements IGenerator {

	private final byte[][] grid;
	private final SeedSelector selector;
	
	private boolean built = false;
	
	private ProceduralPlacingGenerator(ProceduralPlacingGenerator.Builder builder) {
		this.grid = new byte[GRID_SIZE][GRID_SIZE];
		
		this.selector = builder.getselector();
		
		this.createGrid();
	}
	
	public byte getPos(byte x, byte y) {
		if(!built) throw new UnattainableMethodException("Cannot obtain a coordinate if the grid hasn't been created");
		return grid[y][x];
	}
	
	private void createGrid() {
		this.placement((byte)4, (byte)2, (byte)2, (byte)2, Direction.EAST); //Player placement, always similar
		
		final int truckNumber = Math.round(selector.getEntityNumber()*(selector.getTruckPercentage()/100));
		final int carNumber = selector.getEntityNumber()-truckNumber;
		
		for(int i= 0; i < selector.getEntityNumber(); i++) {
			final Coordinate coord = selector.getEntityCoordinate();
			final Direction dir = selector.getEntityDirection();
			
			this.placement((byte)coord.getX(), (byte)coord.getY(), (byte)1, (byte)(i <= carNumber ? 2 : 3), dir);
		}
		
		this.built = true;
	}

	private void placement(byte x, byte y, byte obj, byte size, Direction rot) {
		if(size < 1 || (obj < 0 && obj > 2) || (rot.getValue() < 0 && rot.getValue() > 3)) throw new IllegalArgumentException();
		if(MathHelper.bitCount(obj) > 2 || MathHelper.bitCount(size) > 2 || MathHelper.bitCount(rot.getValue()) > 4) throw new IllegalArgumentException("Unable to perform a byte concatenation");
		this.grid[y][x] = (byte)((obj<<6)+(size<<4)+rot.getValue());
	}
	
	public static ProceduralPlacingGenerator.Builder builder() {
		return new ProceduralPlacingGenerator.Builder();
	}
	
	public static final class Builder {
		
		private SeedSelector selector;
		
		private Builder() {}
		
		public Builder seedSelector(SeedSelector select) {
			this.selector = select;	
			return this;
		}
		
		public ProceduralPlacingGenerator build() {
			return new ProceduralPlacingGenerator(this);
		}
		
		private SeedSelector getselector() {
			return this.selector;
		}
	}
	
}
