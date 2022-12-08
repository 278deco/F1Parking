package fr.f1parking.core.level.gen.backtracking;

import fr.f1parking.core.entities.placement.Coordinate;
import fr.f1parking.core.entities.placement.Direction;
import fr.f1parking.core.io.IOHandler;
import fr.f1parking.core.level.Difficulty;
import fr.f1parking.core.level.gen.RandomSeed;
import fr.f1parking.ui.render.Texture;

public class SeedSelector {
	
	private RandomSeed seed;
	private Difficulty difficulty;
	
	private long base;
	private long offset;
	private long randomByte;
	
	//Every time a request is made, the counter increase by one
	//Only incremented when a car related request is made
	private int requestIncremental;
	
	public SeedSelector(RandomSeed seed, Difficulty diff, int version) {
		this.seed = seed;
		this.difficulty = diff;
		this.requestIncremental = 0;
		
		switch (this.seed.getSeedBitsNumber()) {
			case BYTE_32:
			case USER_GENERATED:
				this.base = this.seed.getSeed() >> 22; //Generate the base selector using the 10 first right bits of the seed
				this.offset = this.seed.getSeed() & 0xf; //Generate the offset selector by taking the first 4 bits
				this.randomByte = (this.seed.getSeed() >> this.offset) & 0xff; //Generate the random byte using the offset and taking 8 bits
				
				if(version != 0) {
//					System.out.println("calc: "+(long)(this.offset* (version/10D)));
//					System.out.println("base: " +this.base);
					this.base = this.base + (long)(this.offset* (version/10D));
//					System.out.println("base: " +this.base);
				}
				break;
			case BYTE_64:
				this.base = this.seed.getSeed() >> 44; //Generate the base selector using the 20 first right bits of the seed
				this.offset = this.seed.getSeed() >> 0x3f; //Generate the offset selector by taking the first 6 bits
				this.randomByte = (this.seed.getSeed() >> this.offset) & 0xff; //Generate the random byte using the offset and taking 8 bits
				
				if(version != 0) this.base = this.base + (long)(this.offset* (version/10D));
				break;
		}
	}
	
	public long getBase() {
		return base;
	}
	
	public long getOffset() {
		return offset;
	}
	
	public long getRandomByte() {
		return randomByte;
	}
	
	public int getEntityNumber() {
		return (int)((this.randomByte/100) * (difficulty.getMaxBound()-difficulty.getMinBound()) + difficulty.getMinBound());
	}
	
	public int getTruckPercentage() {
		return (int) (this.randomByte>0 ? this.difficulty.getTruckPercentage()+this.offset : this.difficulty.getTruckPercentage()-this.offset);
	}
	
	public Coordinate getEntityCoordinate() {
		requestIncremental+=1;
		final int x = Math.abs((int)(this.base%6 + this.seed.getSeed() >> (this.seed.getSeedBitsNumber().getWeight()-(requestIncremental % 10))) %6);
		final int y = Math.abs((int)(this.base%6 - this.seed.getSeed() >> (this.seed.getSeedBitsNumber().getWeight()-(requestIncremental % 10))) %6);
		
		return new Coordinate(x,y);
	}
	
	public Direction getEntityDirection() {
		requestIncremental+=1;
		return Direction.of((int)(this.base%3 + this.seed.getSeed() >> (this.seed.getSeedBitsNumber().getWeight()-(requestIncremental % 10))) %3);
	}
	
	public Texture getEntityTexture(byte entitySize, Coordinate coords) {		
		Texture ret = null;
		String playerTextureID = IOHandler.getInstance().getConfiguration().getPlayerCar();
		int versioning = 0;
		
		//System.out.println("NBEUHGH: " +number);
		
		do {
			int number = (int)(this.randomByte * (coords.getX()/10D)) + (coords.getY()*Math.min(1, versioning));
			
			ret = IOHandler.getInstance().getTexturesFile().getRandomCarTexture(number);
			
			versioning+=1;
		}while(ret.equals(IOHandler.getInstance().getTexturesFile().getCarTexture(playerTextureID)));
		
		System.out.println("Texture: " +ret);
		
		return ret;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof SeedSelector && areEqual((SeedSelector)obj);
	}
	
	private boolean areEqual(SeedSelector selector) {
		return selector.seed.equals(this.seed) &&
				selector.difficulty.equals(difficulty);
	}

	public void resetRequestsNumber() {
		this.requestIncremental = 0;
	}
}
