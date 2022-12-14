package fr.f1parking.core.level.gen;

import java.security.SecureRandom;

/**
 * Create a new seed needed to generate a procedural map
 * @see java.security.SecureRandom
 * @see fr.f1parking.core.level.gen.RandomSeed.RandomSeedByte
 * @author 278deco
 * @version 1.0
 * @since 0.1
 */
public class RandomSeed {
	
	private long seed;
	private RandomSeedByte byteWeight;
	
	/**
	 * Create a new seed and generate it
	 * <p>Using a Byte weight of 32 by default</p>
	 */
	public RandomSeed() {
		this.byteWeight = RandomSeedByte.BYTE_32;
		this.seed = generateUseableSeed(new SecureRandom().generateSeed(byteWeight.getWeight()), new SecureRandom().generateSeed(byteWeight.getWeight()));
	}
	
	/**
	 * Create a new seed and generate it
	 * 
	 * @param byteWeight - the byte weight applied to the seed
	 */
	public RandomSeed(RandomSeedByte byteWeight) {
		if(byteWeight == RandomSeedByte.USER_GENERATED) throw new IllegalArgumentException("The byte weight cannot be zero");
		this.byteWeight = byteWeight;
		this.seed = generateUseableSeed(new SecureRandom().generateSeed(byteWeight.getWeight()), new SecureRandom().generateSeed(byteWeight.getWeight()));
	}
	
	/**
	 * Create a new seed and define it value. 
	 * This seed is constant and not computer generated
	 * 
	 * @param seed - the seed to be used
	 */
	public RandomSeed(long seed) {
		this.byteWeight = RandomSeedByte.USER_GENERATED;
		this.seed = seed & 0xffffffffL;
	}
	
	private long generateUseableSeed(byte[] lowByte, byte[] highByte) {
		long ret = 0;
		for(int i = 0; i < lowByte.length; i++) {
			ret = (ret<<8) + ((highByte[i]*highByte[i] | lowByte[i]) & 255);
		}

		return this.byteWeight == RandomSeedByte.BYTE_32 ? ret & 0xffffffffL : ret;
	}
	
	private static byte[] generateByteSeedArray(long seed) {
		byte[] ret = new byte[Byte.SIZE];
		
		for(int i = Byte.SIZE-1; i >= 0; i--) {
			ret[i] = (byte)(seed & 255);
			seed >>= 8;	
		}
		
		return ret;
	}
	
	/**
	 * Regenerate the seed contained in this class
	 * <p>By default it, this method will set the new RandomSeedByte to 32 <br>
	 * <strong>Any call of this method will overwrite the seed contained in this instance</strong></p>
	 * 
	 * @param seed - a seed used to generate a new one
	 */
	public void regenerateSeed(long seed) {
		this.regenerateSeed(generateByteSeedArray(seed), RandomSeedByte.BYTE_32);
	}
	
	/**
	 * Regenerate the seed contained in this class
	 * <p>By default it, this method will set the new RandomSeedByte to 32 <br>
	 * <strong>Any call of this method will overwrite the seed contained in this instance</strong></p>
	 * 
	 * @param byteSeed - an array of seed used by the seed generator
	 */
	public void regenerateSeed(byte[] byteSeed) {
		this.regenerateSeed(byteSeed, RandomSeedByte.BYTE_32);
	}
	
	/**
	 * Regenerate the seed contained in this class
	 * <p><strong>Any call of this method will overwrite the seed contained in this instance</strong></p>
	 * 
	 * @param seed - a seed used to generate a new one
	 * @param weight - an new RandomSeedByte used in the generation
	 */
	public void regenerateSeed(long seed, RandomSeedByte weight) {
		this.regenerateSeed(generateByteSeedArray(seed), weight);
	}
	
	/**
	 * Regenerate the seed contained in this class
	 * <p><strong>Any call of this method will overwrite the seed contained in this instance</strong></p>
	 * 
	 * @param byteSeed - an array of seed used by the seed generator
	 * @param weight - an new RandomSeedByte used in the generation
	 */
	public void regenerateSeed(byte[] byteSeed, RandomSeedByte weight) {
		this.byteWeight = weight;
		this.seed = generateUseableSeed(new SecureRandom(byteSeed).generateSeed(this.byteWeight.weight), new SecureRandom(byteSeed).generateSeed(this.byteWeight.weight));
	}
	
	/**
	 * Get the stored seed in this class
	 * 
	 * @return the seed
	 */
	public long getSeed() {
		return seed;
	}
	
	public RandomSeedByte getSeedBitsNumber() {
		return this.byteWeight;
	}
	
	/**
	 * Compare this instance of RandomSeed with another 
	 * and tell if their are equal
	 * 
	 * @param obj - the object to be compared
	 */
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof RandomSeed) && ((RandomSeed)obj).seed == this.seed;
	}
	
	/**
	 * Get a string representation of the seed
	 */
	@Override
	public String toString() {
		return "Random Seed ("+this.byteWeight+"): "+getSeed();
	}
	
	/**
	 * Represent the number of byte computed into the seed
	 * 
	 * @author 278deco
	 * @version 1.0
	 * @since 0.1
	 */
	public enum RandomSeedByte {
		USER_GENERATED(32),
		BYTE_32(32),
		BYTE_64(64);

		private int weight;
		RandomSeedByte(int w) {
			this.weight = w;
		}
		
		/**
		 * Get the weight of the seed byte used
		 * 
		 * @return the weight
		 */
		public int getWeight() {
			return weight;
		}
		
		/**
		 * Get a string representation of the seed byte
		 */
		@Override
		public String toString() {
			return "Weight: "+this.name()+"["+this.weight*8+"b]";
		}
	}
}
