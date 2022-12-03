package fr.f1parking.core.level.gen.generators;

import fr.f1parking.core.level.gen.RandomSeed;

public class GeneratorSeedSelector {
	
	private RandomSeed seed;
	
	private long base;
	private long randomBit;
	private long offset;
	
	private GeneratorSeedSelector(Builder b) {
		this.seed = b.getSeed();
		
		this.base = this.seed.getSeed() 
	}
	
	
	
	public static GeneratorSeedSelector.Builder builder() {
		return new GeneratorSeedSelector.Builder();
	}
	
	public static final class Builder {
		
		private RandomSeed seed;
		
		private Builder() { }
		
		public void seed(RandomSeed rdmSeed) {
			this.seed = rdmSeed;
		}
		
		public GeneratorSeedSelector build() {
			return new GeneratorSeedSelector(this);
		}
		
		private RandomSeed getSeed() {
			return this.seed;
		}
		
	}
	
}
