package fr.f1parking.core.level.gen.generators;

import fr.f1parking.core.entities.placement.Direction;
import fr.f1parking.core.helpers.MathHelper;
import fr.f1parking.core.level.Difficulty;
import fr.f1parking.core.level.gen.IGenerator;
import fr.f1parking.core.level.gen.RandomSeed;

public class ProceduralPlacingGenerator implements IGenerator {

	private final byte[][] grid;
	private final RandomSeed seed;
	private final Difficulty difficulty;
	
	private ProceduralPlacingGenerator(Builder b) {
		this.grid = new byte[GRID_SIZE][GRID_SIZE];
		this.seed = b.getSeed();
		this.difficulty = b.getDifficulty();
	}
	
	public byte getPos(byte x, byte y) {
		return grid[y][x];
	}
	
	public void buildGrid() {
		this.placement((byte)4, (byte)2, (byte)2, (byte)2, Direction.EAST); //Player placement, always similar
		
		final int entityNumber = 0;
		//TODO
	}
	
	private int selectRandomSeedPart() {
		//TODO
		return 0;
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
		
		private RandomSeed seed;
		private Difficulty diff;
		
		private Builder() { }
		
		public void seed(RandomSeed rdmSeed) {
			this.seed = rdmSeed;
		}
		
		public void difficulty(Difficulty difficult) {
			this.diff = difficult;
		}
		
		public ProceduralPlacingGenerator build() {
			return new ProceduralPlacingGenerator(this);
		}
		
		private RandomSeed getSeed() {
			return this.seed;
		}
		
		private Difficulty getDifficulty() {
			return this.diff;
		}
		
	}
	
}
